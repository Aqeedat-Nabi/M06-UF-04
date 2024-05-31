import csv

import product_db
import category_db
import subcategory_db

class CSVReader:
    def __init__(self, file_path):
        self.file_path = file_path

    def read_csv_line_by_line(self):
        try:
            with open(self.file_path, newline='', encoding='utf-8') as csvfile:
                reader = csv.reader(csvfile)
                for row in reader:
                    yield row
        except Exception as e:
            print(f"Error reading CSV file: {str(e)}")


    async def load_products(file: UploadFile = File(...)):
        try:
            contents = await file.read()
            contents = contents.decode("utf-8").splitlines()

        for row in csv.reader(contents):
            id_categoria, category_name, id_subcategory, subcategory_name, product_name,  = row
            subcategory = subcategory_db.checkByID_subcategory(id_categoria)

            #category = category_db.get_category_by_name(category_name)
            if not subcategory:
                #hacemos insert
                pass

            else:
                if subcategory != subcategory_name:
                    #hacemos update
                    pass
                else:
                    #pasamos
                    pass


            if not category:
                category_id = category_db.create_category(category_name)
                category = {"category_id": category_id}

            subcategory = subcategory_db.get_subcategory_by_name(subcategory_name)
            if not subcategory:
                subcategory_id = subcategory_db.create_subcategory(subcategory_name, category["category_id"])
                subcategory = {"subcategory_id": subcategory_id}


            product = product_db.get_product_by_name(product_name)
            if not product:
                product_id = product_db.create_product(product_name, subcategory["subcategory_id"])
            else:
                product_db.update_product(product["product_id"], product_name, subcategory["subcategory_id"])

        return {"message": "Products loaded successfully"}

    except Exception as e:
        return {"error": f"An error occurred: {e}"}



# Ejemplo de uso
csv_reader = CSVReader("llista_productes.csv")
for line in csv_reader.read_csv_line_by_line():
    print(line)


