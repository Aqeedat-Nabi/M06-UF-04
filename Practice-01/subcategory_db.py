from client import db_client

def read_subcategories():

    try:
        conn = db_client()
        cur = conn.cursor()
        cur.execute("SELECT * FROM subcategory;")
        result = cur.fetchall()

    except Exception as e:
        return {"status":-1,"message":f"Error de conexio:{e}"}
    
    finally:
        conn.close()

    return result

 

def create_subcategory(name_subcategory,category_id):
    
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "INSERT INTO subcategory (name, category_id) VALUES (%s, %s);"
        values = (name_subcategory, category_id)
        cur.execute(query, values)
        conn.commit()
        subcategory_id = cur.lastrowid
        return {"status": 0, "message": "Subcategoría creada exitosamente", "subcategory_id": subcategory_id}
    except Exception as e:
        return {"status": -1, "message": f"Error de conexión: {e}"}
      

def get_subcategory_id_by_name(subcategory_name):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT subcategory_id FROM subcategory WHERE name = %s;"
        cur.execute(query, (subcategory_name,))
        result = cur.fetchone()
        if result:
            return result[0]
        else:
            return None
    except Exception as e:
        return None
 

def get_subcategory_by_name(nombre):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "SELECT * FROM subcategory WHERE name = %s;"
        cur.execute(query, (nombre,))
        result = cur.fetchone()
        return result
    except Exception as e:
        return {"status": -1, "message": f"Connection Error: {e}"}

    finally:
        conn.close()


def update_subcategory(subcategory_id, subcategory_name, category_id):
    try:
        conn = db_client()
        cur = conn.cursor()
        query = "UPDATE subcategory SET name = %s, category_id = %s, updated_at = CURRENT_TIMESTAMP WHERE subcategory_id = %s;"
        cur.execute(query, (subcategory_name, category_id, subcategory_id))
        conn.commit()
        return True
    except Exception as e:
        return False



