from client import db_client

def read_products():
    try:
        conn = db_client()
        cur = conn.cursor()
        cur.execute("SELECT * FROM product;")
        result = cur.fetchall()

    except Exception as e:
        return {"status " : -1 , "message " : f"Connection Error : {e}"}

    finally:
        conn.close()

    return result


def create_product(name, description, company, price, units, subcategory):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "INSERT INTO product (name, description, company, price, units, subcategory_id) VALUES (%s, %s, %s, %s, %s, %s);"
        values = (name, description, company, price, units, subcategory)
        cur.execute(query,values)
        conn.commit()
        product_id = cur.lastrowid
        
    except Exception as e:
        return {"status " : -1 , "message " : f"Connection Error : {e}"}

    finally:
        conn.close()

    return product_id


def update_product(id, name, description, company, price, units, subcategory):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "UPDATE product SET name=%s, description=%s, company=%s, price=%s, units=%s, subcategory_id=%s WHERE product_id=%s;"
        values = (name, description, company, price, units, subcategory, id)
        cur.execute(query, values)
        conn.commit()
        return {"status": 0, "message": "Product updated successfully"}
    except Exception as e:
        return {"status": -1, "message": f"Connection Error: {e}"}
    

def delete_product(id):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "DELETE FROM product WHERE product_id = %s;"
        cur.execute(query, (id,))
        conn.commit()
        return {"status": 0, "message": "Product deleted successfully"}
    except Exception as e:
        return {"status": -1, "message": f"Connection Error: {e}"}


def get_product_by_id(id):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT * FROM product WHERE product_id = %s;"
        cur.execute(query, (id,))
        result = cur.fetchone()
        return result
    except Exception as e:
        return {"status": -1, "message": f"Connection Error: {e}"}
    finally:
        conn.close()


def read_product_fields(products):
    return [get_product_fields(product) for product in products]


def get_product_fields(product):
    return {
        "id": product[0],
        "name": product[1],
        "description": product[2],
        "company": product[3],
        "price": product[4],
        "units": product[5],
        "subcategory_id": product[6]
    }


def product_schema(product) -> dict:
    return {
        "Id":product[0],
        "name":product[1],
        "description":product[2],
        "company":product[3],
        "price":product[4],
        "price":product[5],
        "units":product[6],
        "subcategoria":product[7]
        }


def get_category_knowing_subcategory(id_subc) -> int:
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT category_id FROM subcategory WHERE subcategory_id = %s;"
        cur.execute(query,(id_subc,))
        result = cur.fetchall()
        conn.commit()
        if result:
            return result[0]
        else:
            return -1
        
    except Exception as e:
        return -1
    finally:
        conn.close()


def get_product_fields(product) -> dict:
    categoria_id = get_category_knowing_subcategory(product[6])
    return {
        "categoria producte":categoria_id,
        "name":product[1],
        "subcategoria producte":product[6],
        "company":product[3],
        "price":product[4],
        }


def read_product_fields(products)->dict:
      return [get_product_fields(product) for product in products]


def get_products_schema(products)->dict:
    return [product_schema(product) for product in products]


def get_products_by_id(id)->dict:
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT * FROM product WHERE product_id = %s;"
        cur.execute(query, (id,))
        result = cur.fetchone()  
        conn.commit()
       
        if result:  
            return result
        else:
            return {"status": -1, "message": "Este producto no esta en la bd."}
        return result  
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}
    finally:
        conn.close()



