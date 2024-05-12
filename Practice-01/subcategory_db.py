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

 