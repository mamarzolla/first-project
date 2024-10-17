export type IshoeList = Ishoe[]

export interface Ishoe {
  id: number
  name: string
  category: Category
  price: number
  description: string
  image: any
  bestSeller: boolean
  rating: number
  availableColors: string[]
  availableSizes: string[]
}

export interface Category {
  id: number
  name: string
}