package com.varkashy.demo.core.java.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Date;

public class DemoApplicationMongoDb {

    public static void main(String[] args) {

        try {

            /**** Connect to MongoDB ****/
            // Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient("localhost", 27017);

            /**** Get database ****/
            // if database doesn't exists, MongoDB will create it for you
            DB db = mongo.getDB("varun_db");

            /**** Get collection / table from 'varun_db' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection table = db.getCollection("person");

            /**** Insert ****/
            // create a document to store key and value
            BasicDBObject document = new BasicDBObject();
            document.put("firstname", "varun");
            document.put("lastname", "kashyap");
            document.put("createdDate", new Date());
            table.insert(document);

            /**** Find and display ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("firstname", "varun");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            /**** Update ****/
            // search document where name="varun" and update it with new values
            BasicDBObject query = new BasicDBObject();
            query.put("firstname", "varun");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("firstname", "varun-updated");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            table.update(query, updateObj);

            /**** Find and display ****/
            BasicDBObject searchQuery2
                    = new BasicDBObject().append("firstname", "varun-updated");

            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println(cursor2.next());
            }

            /**** Done ****/
            System.out.println("Done");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
