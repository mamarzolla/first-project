package it.corso.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.dao.CategoryDao;
import it.corso.dao.ColorDao;
import it.corso.dao.ShoeDao;
import it.corso.dao.SizeDao;
import it.corso.dto.ShoeDto;
import it.corso.dto.InsertShoe;
import it.corso.dto.ListShoeDto;
import it.corso.helper.Response;
import it.corso.model.Category;
import it.corso.model.Color;
import it.corso.model.Shoe;
import it.corso.model.Size;
@Service
@CacheConfig(cacheNames = {"Shoes"})
public class ShoeServiceImpl implements ShoeService {
@Autowired
private ShoeDao shoeDao;
@Autowired
private CategoryDao categoryDao;
@Autowired
private SizeDao sizeDao;
@Autowired
private ColorDao colorDao;
@Autowired
private ModelMapper mapper;



	@Override
	@Cacheable
	public List<ListShoeDto> shoesList() {
		// return (List<Shoe>) shoeDao.findAll();
		List<Shoe> shoes = (List<Shoe>) shoeDao.findAll();
		List<ListShoeDto> shoesDto = new ArrayList<>();
		shoes.forEach(s ->{ //shoesDto.add(mapper.map(s, ShoeDto.class));
			ListShoeDto shoeDto = mapper.map(s, ListShoeDto.class);
			
			shoeDto.setImage("downloadImg/"+ s.getId());
			
			List<String> colorList =s.getAvailableColors().stream()
					.map(Color::getColor)
					.collect(Collectors.toList());
			shoeDto.setAvailableColors(colorList);
			
			
			List<String> sizeList = s.getAvailableSizes().stream()
					.map(Size::getSize)
					.collect(Collectors.toList());
			shoeDto.setAvailableSizes(sizeList);
			
			shoesDto.add(shoeDto);		
			});
		return shoesDto;
	}

	@Override
	@Cacheable(value = "singleArticle", key="#id", sync = true)
	public Object findShoeById(Integer id) {
		Optional<Shoe> shoeOpt = shoeDao.findById(id);
		if(!shoeOpt.isPresent())
			return new Response(404,"data not found");
		ShoeDto shoeDto = mapper.map(shoeOpt.get(), ShoeDto.class);
		shoeDto.setImage("downloadImg/"+shoeOpt.get().getId());
		return shoeDto;
	
	}

	@Override
	@Caching(
			evict = {
					@CacheEvict(cacheNames = "Shoes", allEntries = true )
			}
			)
	public Response setNewShoe(Shoe newShoe) {
		try {   
			
			Optional<Shoe> existingShoe = shoeDao.findByName(newShoe.getName());
			if (existingShoe.isPresent())
				return new Response(400,"shoe name already existing");		
			else if(newShoe.getId()!=null)
				return new Response (400,"id not required");
			//else if(newShoe.getImg()!= null)
			//	return new Response (400,"image handled separately");
			
			
			if (newShoe.getCategory()!= null && newShoe.getCategory().getName() != null) {
				Optional<Category> getCategory = categoryDao.findById(newShoe.getCategory().getId());
   			    if (!getCategory.isPresent()) 
		            return new Response(404, "Category not found");	        
		        newShoe.setCategory(getCategory.get());
			}

			// Gestione dei colori
			List<Color> newColors = new ArrayList<>();
			for (Color color : newShoe.getAvailableColors()) {
			    Optional<Color> existingColor = colorDao.findById(color.getId());
			    if (!existingColor.isPresent())
			        return new Response(404, "Color with id " + color.getId() + " not found");
			    newColors.add(existingColor.get());  
			}
			newShoe.setAvailableColors(newColors);

			// Gestione delle taglie
			List<Size> newSizes = new ArrayList<>();
			for (Size size : newShoe.getAvailableSizes()) {
			    Optional<Size> existingSize = sizeDao.findById(size.getId());
			    if (!existingSize.isPresent())
			        return new Response(404, "Size with id " + size.getId() + " not found");
			    newSizes.add(existingSize.get());  
			}
			newShoe.setAvailableSizes(newSizes);
			
			shoeDao.save(newShoe);
			return new Response(201,"saved succesful");		
		} catch (DataIntegrityViolationException e) {
			return new Response(400,e.getMessage());
		}
	}

	@Override
	@Caching(
			evict = {
					@CacheEvict(cacheNames = "Shoes", allEntries = true ),
					@CacheEvict(cacheNames =  "singleArticle", key="#id")
			}
			)
	public Response modShoe(Integer id, Shoe newShoe) {
		Optional<Shoe> shoeOpt = shoeDao.findById(id);
		if(shoeOpt.isEmpty())
			return new Response(404,"shoe not found");
		Shoe existingShoe = shoeOpt.get();
		BeanUtils.copyProperties(newShoe, existingShoe, "id", "category", "availableColors", "availableSizes");
		
		List<Size> newSizes = new ArrayList<Size>();
		 for (Size size : newShoe.getAvailableSizes()) {
		        Optional<Size> sizeOpt = sizeDao.findById(size.getId());
		        if (!sizeOpt.isPresent()) {
		            // Restituisci una risposta personalizzata se la taglia non viene trovata
		            return new Response(404, "size not found");
		        }
		        newSizes.add(sizeOpt.get());
		    }
		 existingShoe.setAvailableSizes(newSizes);
		 
		 List<Color> newColors = new ArrayList<Color>();
		 for (Color col : newShoe.getAvailableColors()) {
		        Optional<Color> colorOpt = colorDao.findById(col.getId());
		        if (!colorOpt.isPresent()) {
		            // Restituisci una risposta personalizzata se la taglia non viene trovata
		            return new Response(404, "size not found");
		        }
		        newColors.add(colorOpt.get());
		    }
		 existingShoe.setAvailableColors(newColors);
			if (newShoe.getCategory()!= null && newShoe.getCategory().getName() != null) {
				Optional<Category> getCategory = categoryDao.findById(newShoe.getCategory().getId());
   			    if (!getCategory.isPresent()) 
		            return new Response(404, "Category not found");	        
		        existingShoe.setCategory(getCategory.get());
			}
		 
			
		 shoeDao.save(existingShoe);
		return new Response(200, "shoe updated correctly");
	}

	@Override
	@Caching(
			evict = {
					@CacheEvict(cacheNames = "Shoes", allEntries = true ),
					@CacheEvict(cacheNames =  "singleArticle", key="#id")
			}
			)
	public Response deleteShoe(Integer id) {
		Optional<Shoe> shoeOpt = shoeDao.findById(id);
		if(!shoeOpt.isPresent())
			return new Response(404,"data not found");
		shoeDao.deleteById(id);
		return new Response(202,"shoe deleted");
	}

	@Override
	public Response setListShoes(List<InsertShoe> newShoes) {
	    for (InsertShoe newShoe : newShoes) {
	        try {
	        	Shoe shoe = new Shoe();
	        	shoe.setName(newShoe.getName());
	        	 Optional<Category> category = categoryDao.findByName(newShoe.getCategory());
	             if (category.isPresent()) {
	                 shoe.setCategory(category.get());
	             } else {
	                 return new Response(404, "Category " + newShoe.getCategory() + " not found");
	             }
	             List<Color> newColors = new ArrayList<>();
	             for (String colorName : newShoe.getAvailableColors()) {
	                 Optional<Color> color = Optional.ofNullable(colorDao.findByColor(colorName));
	                 if (color.isPresent()) {
	                     newColors.add(color.get());
	                 } else {
	                     return new Response(404, "Color " + colorName + " not found");
	                 }
	             }
	             shoe.setAvailableColors(newColors);
	             List<Size> newSizes = new ArrayList<>();
	             for (String sizeName : newShoe.getAvailableSizes()) {
	                 Optional<Size> size = Optional.ofNullable(sizeDao.findBySize(sizeName));
	                 if (size.isPresent()) {
	                     newSizes.add(size.get());
	                 } else {
	                     return new Response(404, "Size " + sizeName + " not found");
	                 }
	             }
	             shoe.setPrice(newShoe.getPrice());
	             shoe.setAvailableSizes(newSizes);
	             shoe.setDescription(newShoe.getDescription());
	             shoe.setBestSeller(newShoe.isBestSeller());
	             shoe.setRating(newShoe.getRating());
	             shoeDao.save(shoe);
	             
	            
	        } catch (DataIntegrityViolationException e) {
	            return new Response(400, e.getMessage());
	        }
	    }
	    return new Response(201, "Saved successfully");
	}
	@Caching(
			evict = {
					@CacheEvict(cacheNames = "Shoes", allEntries = true ),
					@CacheEvict(cacheNames = "shoesImages", allEntries = true),
					@CacheEvict(cacheNames =  "singleArticle", key="#id")
			}
			)
	   public Response addImageToShoe(Integer id, MultipartFile image) {
	        try {
	            Shoe shoe = shoeDao.findById(id).orElseThrow(() -> new RuntimeException("Shoe not found"));

	            // Gestione della codifica dell'immagine in base64
	            String format = image.getContentType();
	            String codeImage = "data:" + format + ";base64," + Base64.getEncoder().encodeToString(image.getBytes());
	            shoe.setImg(codeImage);

	            shoeDao.save(shoe);
	            return new Response(200, "Immagine caricata con successo");
	        } catch (IOException e) {
	            return new Response(400, "Errore durante la codifica dell'immagine");
	        } catch (RuntimeException e) {
	            return new Response(404, e.getMessage());
	        }
	    }
	 @Cacheable(value = "shoesImages", key = "#id")
	   public byte[] getImgbyShoeId(Integer id) {
		   Shoe shoe = shoeDao.findById(id).get();
		   String code = shoe.getImg();
		   String [] parts= code.split(",");
		   String imageData= parts[1];
		   return Base64.getDecoder().decode(imageData);
	   }
}
