package com.project.springboot;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.Consumes;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/cinema")
public class MovieController {
    private final String xmlFilePath = "classpath:movie1.xml";

    @GetMapping("/movies")
    public List<Movie> getAllMovies() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        NodeList nodeList = (NodeList) xPath.compile("//cinema/movies/movie").evaluate(doc, XPathConstants.NODESET);

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Movie movie = new Movie();
                movie.setId(element.getAttribute("ID"));
                movie.setName(element.getElementsByTagName("nameF").item(0).getTextContent());
                movie.setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
                movie.setCategory(element.getElementsByTagName("category-ref").item(0).getAttributes().getNamedItem("ref").getNodeValue());
                movie.setActor(element.getElementsByTagName("actor-ref").item(0).getAttributes().getNamedItem("ref1").getNodeValue());
                movies.add(movie);
            }
        }
        return movies;
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        String expression = String.format("//cinema/movies/movie[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            return null;
        }

        Element element = (Element) node;
        Movie movie = new Movie();
        movie.setId(element.getAttribute("ID"));
        movie.setName(element.getElementsByTagName("nameF").item(0).getTextContent());
        movie.setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
        movie.setCategory(element.getElementsByTagName("category-ref").item(0).getAttributes().getNamedItem("ref").getNodeValue());
        movie.setActor(element.getElementsByTagName("actor-ref").item(0).getAttributes().getNamedItem("ref1").getNodeValue());
        return movie;
    }

    @GetMapping("/actors")
    public List<Actor> getAllActors() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        NodeList nodeList = (NodeList) xPath.compile("//cinema/actors/actor").evaluate(doc, XPathConstants.NODESET);

        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Actor actor = new Actor();
                actor.setId(element.getAttribute("ID"));
                actor.setFirstName(element.getElementsByTagName("firstname").item(0).getTextContent());
                actor.setLastName(element.getElementsByTagName("lastname").item(0).getTextContent());
                actor.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));
                actors.add(actor);
            }
        }

        return actors;
    }

    @GetMapping("/actors/{id}")
    public Actor getActorById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        String expression = String.format("//cinema/actors/actor[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            return null;
        }

        Element element = (Element) node;
        Actor actor = new Actor();
        actor.setId(element.getAttribute("ID"));
        actor.setFirstName(element.getElementsByTagName("firstname").item(0).getTextContent());
        actor.setLastName(element.getElementsByTagName("lastname").item(0).getTextContent());
        actor.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

        return actor;
    }

    //read category
    @GetMapping("/category")
    public List<Category> getAllCategories() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        NodeList nodeList = (NodeList) xPath.compile("//cinema/categories/category").evaluate(doc, XPathConstants.NODESET);

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Category category = new Category();
                category.setId(element.getAttribute("ID"));
                category.setName(element.getElementsByTagName("nameC").item(0).getTextContent());
                category.setAgeRecommendation(Integer.parseInt(element.getElementsByTagName("ageRec").item(0).getTextContent()));
                categories.add(category);
            }
        }

        return categories;
    }

    //read category by id
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        Document doc = db.parse(is);
        String expression = String.format("//cinema/categories/category[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            return null;
        }

        Element element = (Element) node;
        Category category = new Category();
        category.setId(element.getAttribute("ID"));
        category.setName(element.getElementsByTagName("nameC").item(0).getTextContent());
        category.setAgeRecommendation(Integer.parseInt(element.getElementsByTagName("ageRec").item(0).getTextContent()));

        return category;
    }

    //Delete Movie
    @DeleteMapping("/movies/delete/{id}")
    public void deleteMovieById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException, TransformerException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = String.format("//cinema/movies/movie[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + id + " not found");
        }

        node.getParentNode().removeChild(node);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/movieResult.xml"));
        transformer.transform(source, result);
    }


    //Delete Actor
    @DeleteMapping("/actors/delete/{id}")
    public void deleteActorById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException, TransformerException, TransformerException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = String.format("//cinema/actors/actor[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor with id " + id + " not found");
        }

        node.getParentNode().removeChild(node);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/movieResult.xml"));
        transformer.transform(source, result);
    }

    //Delete Category

    @DeleteMapping("/categories/delete/{id}")
    public void deleteCategoryById(@PathVariable String id) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException, TransformerException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie1.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = String.format("//cinema/categories/category[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " not found");
        }

        node.getParentNode().removeChild(node);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/movieResult.xml"));
        transformer.transform(source, result);
    }

    //update

    @PutMapping("/categories/update/{id}")
    public Category updateCategoryById(@PathVariable String id, @RequestBody Category category) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("movie.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = String.format("//cinema/categories/category[@ID='%s']", id);
        Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        if (node == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " not found");
        }

        Element categoryElement = (Element) node;
        categoryElement.getElementsByTagName("nameC").item(0).setTextContent(category.getName());
        categoryElement.getElementsByTagName("ageRec").item(0).setTextContent(String.valueOf(category.getAgeRecommendation()));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/movieResult.xml"));
        transformer.transform(source, result);

        return category;
    }

    @PutMapping("/movies/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("ID") String ID, @RequestBody Movie updatedMovie) throws Exception {
        File xmlFile = new File("movie1.xml");
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/cinema/movies/movie[@ID='" + ID + "']";
        Node movieNode = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);

        if (movieNode == null) {
            return ResponseEntity.notFound().build();
        }

        // actualizam anul si categoria filmului
        Element movieElement = (Element) movieNode;
        movieElement.getElementsByTagName("year").item(0).setTextContent(String.valueOf(updatedMovie.getYear()));
        movieElement.getElementsByTagName("category-ref").item(0).getAttributes().getNamedItem("ref").setTextContent(updatedMovie.getCategory());

        // salvam modificarile in fisier
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(xmlFile);
        Source input = new DOMSource(document);
        transformer.transform(input, output);

        return ResponseEntity.ok(updatedMovie);
    }

    @Consumes("application/xml")
    @PostMapping("/actors")
    public ResponseEntity<?> addActor(@RequestBody Actor actor) {
        try {
            File file = new File("src/main/resources/movie1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile("/cinema/actors").evaluate(doc, XPathConstants.NODESET);
            Node actorsNode = nodeList.item(0);
            Element actorElement = doc.createElement("actor");
            actorElement.setAttribute("ID", actor.getId());
            Element firstnameElement = doc.createElement("firstname");
            firstnameElement.setTextContent(actor.getFirstName());
            actorElement.appendChild(firstnameElement);
            Element lastnameElement = doc.createElement("lastname");
            lastnameElement.setTextContent(actor.getLastName());
            actorElement.appendChild(lastnameElement);
            Element ageElement = doc.createElement("age");
            ageElement.setTextContent(String.valueOf(actor.getAge()));
            actorElement.appendChild(ageElement);
            actorsNode.appendChild(actorElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/movieResult.xml"));
            transformer.transform(source, result);
            return ResponseEntity.ok("Actor added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding actor");
        }
    }

    @PostMapping("/validate")
    public String validateMovie(@RequestBody String xml) throws SAXException, IOException {
        // create a new schema factory
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // create a new schema from the XSD file
        Schema schema = factory.newSchema(new ClassPathResource("movie1.xsd").getFile());

        // create a new validator instance
        Validator validator = schema.newValidator();

        // validate the XML against the schema
        validator.validate(new StreamSource(new StringReader(xml)));

        return "XML is valid";
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getActors() throws Exception {
        // Load the XML file
        File xmlFile = new File("src/main/resources/movie1.xml");
        // se creează un obiect de tip File cu numele și calea către fișierul XML ce conține informațiile despre actori
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xml = builder.parse(xmlFile);

        // Load the XSLT file
        File xsltFile = new File("src/main/resources/cinema.xsl");
        // se creează un obiect de tip File cu numele și calea către fișierul XSLT ce va fi folosit pentru transformarea XML în HTML
        StreamSource xslt = new StreamSource(xsltFile);

        // Create the transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(xslt);

        // Perform the transformation
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(xml), new StreamResult(writer));
        // se transformă XML-ul în HTML și se stochează output-ul în obiectul de tip StringWriter


        return writer.toString();
    }

    @GetMapping("/xquery")
    public String xquery() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><cinema><movies><movie ID=\"1\"><nameF>John Wick</nameF><year>2014</year><category-ref ref=\"Action\"/><actor-ref ref1=\"Marry\"/></movie><movie ID=\"2\"><nameF>The Guilty</nameF><year>2021</year><category-ref ref=\"Thriller\"/><actor-ref ref1=\"John\"/></movie><movie ID=\"3\"><nameF>Jumanji - Aventura in jungla</nameF><year>2017</year><category-ref ref=\"Comedy\"/><actor-ref ref1=\"Kevin\"/></movie></movies><actors><actor ID=\"Dwayne\"><firstname>Dwayne</firstname><lastname>Johnson</lastname><age>51</age></actor><actor ID=\"Keanu\"><firstname>Keanu</firstname><lastname>Reeves</lastname><age>58</age></actor><actor ID=\"Jake\"><firstname>Jake</firstname><lastname>Gyllenhaal</lastname><age>43</age></actor><actor ID=\"\"><firstname/><lastname/><age>65</age></actor></actors><categories><category ID=\"Action\"><nameC>Action</nameC><ageRec>12</ageRec></category><category ID=\"Comedy\"><nameC>Comedy</nameC><ageRec>13</ageRec></category><category ID=\"Thriller\"><nameC>Thriller</nameC><ageRec>13</ageRec></category></categories></cinema>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//movie[nameF='John Wick']/@ID");
        String result = (String) expr.evaluate(doc, XPathConstants.STRING);

        return "The ID of John Wick is: " + result;
    }



}


