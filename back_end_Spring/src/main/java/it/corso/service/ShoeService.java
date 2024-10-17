package it.corso.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.corso.dto.InsertShoe;
import it.corso.dto.ListShoeDto;
import it.corso.helper.Response;
import it.corso.model.Shoe;

public interface ShoeService {
	
	List<ListShoeDto> shoesList();
	Object findShoeById (Integer id);
	Response setNewShoe(Shoe shoe);
	Response modShoe(Integer id, Shoe newShoe);
	Response deleteShoe (Integer id);
	Response setListShoes(List<InsertShoe> newShoes);
	Response addImageToShoe(Integer id, MultipartFile image);
	public byte[] getImgbyShoeId(Integer id);
}
