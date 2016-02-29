package com.thoughtworks.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class MongodbUtil {

    private MongoClient mongoClient;

    private MongodbUtil(){
        mongoClient = new MongoClient();
    }

    private static final MongodbUtil mongodbUtil = new MongodbUtil();

    public static MongodbUtil getMongodbInstance(){
        return  mongodbUtil;
    }

    public MongoCollection getMongoBookCollection(){
        MongoDatabase mongoDatabase = mongoClient.getDatabase("bookself");
        MongoCollection mongoCollection  = mongoDatabase.getCollection("books");
        return mongoCollection;
    }

    private JsonArray convertToJsonArray(FindIterable findIterable){
        JSON json = new JSON();
        String jsonString =  json.serialize(findIterable);

        JsonParser jsonParser = new JsonParser();
        JsonArray element = (JsonArray)jsonParser.parse(jsonString);
        return element;
    }

    public JsonArray getAllBooks(){
        return convertToJsonArray(getMongoBookCollection().find());
    }

    public JsonArray getBookInfoByTitle(String string){
        Pattern pattern = Pattern.compile(".*"+string+".*");
        System.out.print(pattern.toString());
        return convertToJsonArray(getMongoBookCollection().find(regex("title", pattern.toString())));
    }

    public JsonArray getBookById(String string){
        return convertToJsonArray(getMongoBookCollection().find(eq("id", string)));
    }

}
