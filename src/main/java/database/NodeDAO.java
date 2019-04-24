package database;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;


public class NodeDAO {
    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private MongoCollection table;

    public NodeDAO(Properties prop){
        String clientUrl = "mongodb://" + prop.getProperty("user") + ":" + prop.getProperty("password") + "@" +
                prop.getProperty("host") + ":" + prop.getProperty("port") + "/" + prop.getProperty("dbname");
        MongoClientURI uri = new MongoClientURI(clientUrl);
        mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase(prop.getProperty("dbname"));
        table = mongoDatabase.getCollection(prop.getProperty("table"));
    }

    public void addNode(Node node){
        if(!(getByName(node.getName())==null)){
            System.out.println("ERR! Node with such name already exists: " +  node.getName());
            return;
        }
        Document document = new Document();

        document.put("param1", node.getParam1());
        document.put("param2", node.getParam2());
        document.put("name", node.getName());

        table.insertOne(document);

        System.out.println(node.getName()); //TODO
    }

    public Node getByName(String name){
        Document query = new Document();
        query.put("name", name);
        Document result;
        result = (Document) table.find(query).first();
        if (result==null){
            //TODO: no such document
            return null;
        }
        Node node = new Node();
        node.setParam1(String.valueOf(result.get("param1")));
        node.setParam2(String.valueOf(result.get("param2")));
        node.setName(name);
        return node;
    }

    public void update(Node node){
        Document searchQuery = new Document();
        searchQuery.put("name", node.getName());
        Document newData = new Document();
        if(!(node.getParam1()==null)){
            newData.append("param1", node.getParam1());
        }
        if(!(node.getParam2()==null)){
            newData.append("param2", node.getParam2());
        }
        table.updateMany(searchQuery, new Document("$set", newData));
    }

    public void deleteByName(String name){
        Document query = new Document();
        query.put("name", name);
        table.deleteMany(query);
    }

    public void clearTable(){
        table.deleteMany(new Document());
    }

    public List<Node> getAll(){
        FindIterable<Document> documents = table.find();
        List<Node> nodes = new ArrayList<Node>();
        for (Document doc : documents) {
            Node node = new Node();
            node.setName(String.valueOf(doc.get("name")));
            node.setParam1(String.valueOf(doc.get("param1")));
            node.setParam2(String.valueOf(doc.get("param2")));

            nodes.add(node);
        }
        return nodes;
    }

    /*
    public static void main(String[] args){
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        props.setProperty("host", "localhost");
        props.setProperty("port", "27017");
        props.setProperty("dbname", "admin");
        props.setProperty("table", "nodes");
        NodeDAO dm = new NodeDAO(props);

        Node node1 = new Node("123", "456", "a");
        Node node2 = new Node("1234", "4567", "b");
        Node node3 = new Node("12345", "45678", "c");
        dm.addNode(node1);
        dm.addNode(node2);
        dm.addNode(node3);

        System.out.println("creating nodes...");
        System.out.println("NODE A: " + dm.getByName("a"));
        System.out.println("num of docs: " + dm.getAll().size());
        System.out.println("deleting \'b\'-node...");
        dm.deleteByName("b");
        System.out.println("num of docs: " + dm.getAll().size());

        System.out.println("editing \'c\'-node...");
        Node r = new Node();
        r.setName("c");
        r.setParam2("abc");
        r.setParam1("bde");

        dm.update(r);
        System.out.println("NODE C: " + dm.getByName("c"));
        System.out.println("num of docs: " + dm.getAll().size());

        System.out.println("creating another \'a\'-node...");
        Node node4 = new Node("new", "node", "a");
        dm.addNode(node4);
        System.out.println("num of docs: " + dm.getAll().size());
        System.out.println("NODE A: " + dm.getByName("a"));


        System.out.println("clearing table...");
        dm.clearTable();
        System.out.println("num of docs: " + dm.getAll().size());
    }
    */
}