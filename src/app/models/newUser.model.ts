export type InewUser = InewUser[]

export interface NewUser {
    name: string
    lastName: string
    email: string
    phoneOne: string
    profile: Profile
    addressList: AddressList[]
  }
  
  export interface Profile {
    username: string
    password: string
  }
  
  export interface AddressList {
    street: string
    number: string
    city: string
    province: string
    postalCode: string
  }