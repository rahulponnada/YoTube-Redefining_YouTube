package edu.umkc.db;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoDB {

	private MongoClient mongo;
	private DB db;
	private static DBCollection table;

	

	public MongoDB() {
		setMongo();
		setDb();
	}
	
	public MongoClient getMongo() {
		return mongo;
	}

	public void setMongo() {
		try {
			/**** Connect to MongoDB ****/
			this.mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			System.out.println("Error in connection");
		}
	}

	public DB getDb() {
		return db;
	}

	public void setDb() {
		/**** Get database ****/
		// if database doesn't exists, MongoDB will create it for you
		db = mongo.getDB("yotub");
	}
	public DBCollection getTable(String table) {
		return db.getCollection(table);
	}

	public void insert(BasicDBObject document, DBCollection table) {
		table.insert(document);
	}

	public void delete(DBCollection collection) {
		collection.remove(new BasicDBObject());

	}
	
	public void fetch(String id, DBCollection table ) {
		BasicDBObject searchQuery = new BasicDBObject();
	//	searchQuery.put("videoid", id);
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/*public static void main(String[] args) {
		try {
			
			
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("name", "mkyong");
			document.put("age", 30);
			document.put("createdDate", new Date());
			table.insert(document);

			//**** Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "mkyong");

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			
			/**** Find and display 
			BasicDBObject searchQuery2 = new BasicDBObject().append("name",
					"mkyong-updated");

			DBCursor cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}

			/**** Done 
			System.out.println("Done");

		} catch (MongoException e1) {
			e1.printStackTrace();
		}
	}*/

	
}
