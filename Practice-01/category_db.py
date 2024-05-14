from client import db_client

 # READ all fields from category table 
def read_category():
    
    try:
        conn = db_client()
        cur = conn.cursor()
        cur.execute("SELECT * FROM category;")
        result = cur.fetchall()

    except Exception as e:
        return {"status":-1,"message":f"Error de conexio:{e}"}
    
    finally:
        conn.close()

    return result


# CREATE a new category 
def create_category(name_categoria):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "INSERT INTO category (name) VALUES (%s);"
        values = (name_categoria,)
        cur.execute(query,values)
        conn.commit()
        category_id = cur.lastrowid
        return {"status": 0, "message": "Categoría creada exitosamente", "category_id": category_id}
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}


# READ a category by name providing an id 
def get_category_id_by_name(category_name):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT category_id FROM category WHERE name = %s;"
        cur.execute(query, (category_name,))
        result = cur.fetchone()
        if result:
            return result[0]
        else:
            return None
    except Exception as e:
        return None

def get_category_by_name(nombre):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT * FROM category WHERE name = %s;"
        cur.execute(query, (nombre,))
        result = cur.fetchone()
        return result
    except Exception as e:
        return {"status": -1, "message": f"Connection Error: {e}"}
    finally:
        conn.close()



# UPDATE / MODIFY a category name , given it's id
def update_category(category_id, category_name):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "UPDATE category SET name = %s, updated_at = CURRENT_TIMESTAMP WHERE category_id = %s;"
        cur.execute(query, (category_name, category_id))
        conn.commit()
        return True
    except Exception as e:
        return False
    finally:
        conn.close()
