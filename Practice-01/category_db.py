from client import db_client

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

