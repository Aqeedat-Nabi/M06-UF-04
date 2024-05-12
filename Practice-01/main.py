from typing import Union
from typing import List
from fastapi import FastAPI

import product_db
import category_db
import subcategory_db

from pydantic import BaseModel

## To start the server
# command :  uvicorn main:app --reload
# and the above cmd gives us a url , hence we 
## get the url and by clicking it gives us the landing page in the browser and we can see the results of the methods below : 

# (http://127.0.0.1:8000) --> link + (/docs) = swagger 

app = FastAPI()


class Subcategory(BaseModel):
    name_subcategory:str
    categoria_id:int
    

class Category(BaseModel):
    name_category:str
    

class Product(BaseModel):
    name_p:str
    description_p:str
    company_p:str
    price_p: float
    units_p: int
    subcategory_id_p: int


@app.get("/")
def read_root():
    return {"Store Product API "}

# 1 - Route:  /product/
#Type of request: Get
#Operation: Returns a json list with all the product information from 
#the products table	
# Get all products
@app.get("/products/")
def read_products():
    return product_db.get_products_schema(product_db.read_products())


# 2 - Route:  /product/{id}
#Type of request: Get
#Operation: Returns a json object with the product information that the id of 
#the databases matches the id that we receive by parameter.	
# Get product by ID
@app.get("/product/{id}")
def read_by_id_product(id):
    return product_db.get_products_by_id(id)


# 3 - Route:  /product/
#Type of request: Post
#Operation: Allows you to add a new product to the BBDD 
#Returns an object json with the message "Added successfully"	
# Add a new product
@app.post("/product/")
def create_product(data: Product):
    name = data.name_p
    desc = data.description_p
    company = data.company_p
    price = data.price_p
    units = data.units_p
    subcategory = data.subcategory_id_p
    product_id = product_db.create_product(name, desc, company, price, units, subcategory)
    return {
        "message": "Added successfully",
        "product_id": product_id
    }


# 4 - Route:   /product/product/{id}
#Type of request: Put
#Operation: Allows you to modify the field of a BBDD product
#  defined by the id that reach by parameter
#Returns a json object with the message "Modified successfully"
# Update product
@app.put("/product/{id}")
def update_product(id: int, data: Product):
    name = data.name_p
    desc = data.description_p
    company = data.company_p
    price = data.price_p
    units = data.units_p
    subcategory = data.subcategory_id_p
    return product_db.update_product(id, name, desc, company, price, units, subcategory)


# 5 - Route:  /product/{id}
#Type of request: Delete
#Operation: Allows you to remove a product from the BBDD 
#Returns an object json with the message "Deleted successfully"	
# Delete product
@app.delete("/product/{id}")
def delete_product(id: int):
    product_db.delete_product(id)
    return {"message": "Deleted successfully"}


# 6 - Route:  /productAll/
#Type of request: Get (All the products)
#Operation: Returns a json list with the following information: 
#category name,	subcategory name, product name, product brand and price.
# Get all product details
@app.get("/productAll/")
def get_product_details():
    return product_db.read_product_fields(product_db.read_products())

# Read categories
@app.get("/category/")
def read_categories():
    return category_db.read_category()

# Create category
@app.post("/category/")  
def create_category(data: Category):
    name = data.name_category
    result = category_db.create_category(name)
    return {"message": "Category created successfully", "category_info": result}

# Read subcategories
@app.get("/subcategory/")
def read_subcategories():
    return subcategory_db.read_subcategories()

# Create subcategory
@app.post("/subcategory/")
def create_subcategory(data: Subcategory):
    name = data.name_subcategory
    category_id = data.categoria_id
    subcategory_id = subcategory_db.create_subcategory(name, category_id)
    return {"message": "Subcategory created successfully", "subcategory_id": subcategory_id}
