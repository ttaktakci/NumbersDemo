package com.company.dao;

import com.company.model.Entity;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public abstract class EntityDao<T extends Entity> {

    private MongoDatabase db = (new MongoClient("localhost", 27017)).getDatabase("mydb");
    private MongoCollection<Document> dbCollection = db.getCollection(getCollectionName());

    abstract String getCollectionName();

    public T add(T entity) {
        entity.setInsertDate(getDate());
        Document doc = getDocumentFromEntity(entity);

        dbCollection.insertOne(doc);

        return entity;
    }

    public T get(Long number) {
        Document document = dbCollection.find(eq("number", number)).first();
        return (document != null) ? getEntityFromDocument(document) : null;
    }

    public List<T> getList(String order) {
        List<T> entityList = new ArrayList<>();

        int sortVal = ("desc".equalsIgnoreCase(order)) ? -1 : 1;
        try (MongoCursor<Document> cursor = dbCollection.find().sort(new BasicDBObject("number", sortVal)).iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                T e = getEntityFromDocument(document);
                entityList.add(e);
            }
        }

        return entityList;
    }

    public T remove(T entity) {
        dbCollection.deleteOne(eq("number", entity.getNumber()));
        return entity;
    }

    public T getMax() {
        Document document = dbCollection.find().sort(new BasicDBObject("number", -1)).first();
        return (document != null) ? getEntityFromDocument(document) : null;
    }

    public T getMin() {
        Document document = dbCollection.find().sort(new BasicDBObject("number", 1)).first();
        return (document != null) ? getEntityFromDocument(document) : null;
    }

    private T getEntityFromDocument(Document document) {
        T entity = createEntityWithDocument(document);
        entity.setNumber((long) document.get("number"));
        entity.setInsertDate((String) document.get("insert_date"));
        return entity;
    }

    abstract T createEntityWithDocument(Document document);

    private Document getDocumentFromEntity(T entity) {
        Document document = new Document("number", entity.getNumber())
                .append("insert_date", entity.getInsertDate());
        updateDocumentWithEntity(document, entity);
        return document;
    }

    abstract void updateDocumentWithEntity(Document document, T entity);

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }
}
