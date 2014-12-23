package DreamPackage;

import java.awt.Color;
import java.io.File;

import DreamPackage.Level;
import DreamPackage.Lokum;
import DreamPackage.striped;
import DreamPackage.wrapped;
import DreamPackage.colorBomb;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLG {

	public static Level getSavedState() {

		String XML = "game.xml";
		String XSD = "game.xsd";
		Level level = null;

		if (validateXMLSchema(XSD, XML)) {

			try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory
						.newDocumentBuilder();
				Document doc = docBuilder.parse(new File("game.xml"));

				doc.getDocumentElement().normalize();

				Node stateNode = doc.getElementsByTagName("game").item(0);
				int levelID = 0;
				int score = 0;
				int movesLeft = 0;
				int timeLeft = 0;
				int scoreNeeded = 0;
				int xMax = 0;
				int yMax = 0;
				Lokum[][] board = null;

				if (stateNode.getNodeType() == Node.ELEMENT_NODE) {

					Element stateElement = (Element) stateNode;
					levelID = Integer.parseInt(stateElement
							.getElementsByTagName("level").item(0)
							.getTextContent());
					movesLeft = Integer.parseInt(stateElement
							.getElementsByTagName("movesleft").item(0)
							.getTextContent());
					timeLeft = Integer.parseInt(stateElement
							.getElementsByTagName("timeleft").item(0)
							.getTextContent());
					score = Integer.parseInt(stateElement
							.getElementsByTagName("currentscore").item(0)
							.getTextContent());
					scoreNeeded = Integer.parseInt(stateElement
							.getElementsByTagName("goalscore").item(0)
							.getTextContent());
					/*
					 * xMax = Integer.parseInt(stateElement
					 * .getElementsByTagName("board").item(0)
					 * .getAttributes().item(0).getTextContent()); yMax =
					 * Integer.parseInt(stateElement
					 * .getElementsByTagName("board").item(0)
					 * .getAttributes().item(1).getTextContent());
					 * System.out.println(xMax); System.out.println(xMax);
					 */

					NodeList lokumlist = ((Element) stateElement
							.getElementsByTagName("board").item(0))
							.getElementsByTagName("lokum");

					NodeList obstacleList = ((Element) stateElement
							.getElementsByTagName("board").item(0))
							.getElementsByTagName("obstacle");

					for (int i = 0; i < lokumlist.getLength(); i++) {
						Node lokumNode = lokumlist.item(i);
						Element lokumElement = (Element) lokumNode;
						int x = Integer.parseInt(lokumElement
								.getElementsByTagName("xcoord").item(0)
								.getTextContent());
						int y = Integer.parseInt(lokumElement
								.getElementsByTagName("ycoord").item(0)
								.getTextContent());
						if (x == 0)
							yMax++;
						if (y == 0)
							xMax++;
					}

					for (int i = 0; i < obstacleList.getLength(); i++) {
						Node obstacleNode = obstacleList.item(i);
						Element obstacleElement = (Element) obstacleNode;
						int x = Integer.parseInt(obstacleElement
								.getElementsByTagName("xcoord").item(0)
								.getTextContent());
						int y = Integer.parseInt(obstacleElement
								.getElementsByTagName("ycoord").item(0)
								.getTextContent());
						if (x == 0)
							yMax++;
						if (y == 0)
							xMax++;
					}
					board = new Lokum[xMax][yMax];

					for (int i = 0; i < lokumlist.getLength(); i++) {
						Node lokumNode = lokumlist.item(i);
						Element lokumElement = (Element) lokumNode;
						String colorStr = lokumElement
								.getElementsByTagName("color").item(0)
								.getTextContent();
						int x = Integer.parseInt(lokumElement
								.getElementsByTagName("xcoord").item(0)
								.getTextContent());
						int y = Integer.parseInt(lokumElement
								.getElementsByTagName("ycoord").item(0)
								.getTextContent());
						// int LokumTime = Integer.parseInt(lokumElement
						// .getElementsByTagName("lokumtime").item(0)
						// .getTextContent());
						String type = lokumElement.getElementsByTagName("type")
								.item(0).getTextContent();
						Color c = null;

						if (colorStr.equals("red"))
							c = Lokum.RED;
						if (colorStr.equals("yellow"))
							c = Lokum.YELLOW;
						if (colorStr.equals("green"))
							c = Lokum.GREEN;
						if (colorStr.equals("brown"))
							c = Lokum.BROWN;
						if (type.equals("Lokum"))
							board[x][y] = new NormalLokum(c);
						if (type.equals("tLokum"))
							board[x][y] = new TimedLokum(c);
						if (type.equals("vStriped"))
							board[x][y] = new striped(c, striped.VERTICAL);
						if (type.equals("hStriped"))
							board[x][y] = new striped(c, striped.HORIZONTAL);
						if (type.equals("wrapped"))
							board[x][y] = new wrapped(c);
						if (type.equals("colorBomb"))
							board[x][y] = new colorBomb();
					}

					for (int i = 0; i < obstacleList.getLength(); i++) {
						Node obstacleNode = obstacleList.item(i);
						Element obstacleElement = (Element) obstacleNode;
						/*
						 * String colorStr = obstacleElement
						 * .getElementsByTagName("color").item(0)
						 * .getTextContent();
						 */
						int x = Integer.parseInt(obstacleElement
								.getElementsByTagName("xcoord").item(0)
								.getTextContent());
						int y = Integer.parseInt(obstacleElement
								.getElementsByTagName("ycoord").item(0)
								.getTextContent());
						board[x][y] = new obstacle();
					}

				}

				if (timeLeft == -1) {
					level = new NormalLevel(movesLeft, score, board,
							scoreNeeded, levelID, false);
				} else {
					level = new TimedLevel(movesLeft, score, board,
							scoreNeeded, levelID, false, timeLeft);
				}

			} catch (SAXParseException err) {
				System.out.println("** Parsing error" + ", line "
						+ err.getLineNumber() + ", uri " + err.getSystemId());
				System.out.println(" " + err.getMessage());

			} catch (SAXException e) {
				Exception x = e.getException();
				((x == null) ? e : x).printStackTrace();

			} catch (Throwable t) {
				t.printStackTrace();
			}

		}
		return level;

	}

	public static void SaveState() {
		final String xmlFilePath = "game.xml";

		GameState gs = GameState.getInstance();
		String levelid = String.valueOf(gs.getSelectedLevel().getLevelID());
		String currentscore = String.valueOf(gs.getScore());
		String goalscore = String.valueOf(gs.getSelectedLevel()
				.getScoreNeeded());
		String timeLeft;
		if (gs.getSelectedLevel() instanceof TimedLevel) {
			timeLeft = String.valueOf(((TimedLevel) gs.getSelectedLevel())
					.getRemainingTime());
		} else {
			timeLeft = "-1";
		}

		String movesleft = String.valueOf(gs.getRemainingMoves());

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			// PLAYER NAME VE ID ÇEKİLECEK
			// Creating <game>, <player>, <id>, <name>
			Node game = document.createElement("game");
			document.appendChild(game);
			Node player = document.createElement("player");
			Node id = document.createElement("id");
			id.setTextContent("1");
			Node name = document.createElement("name");
			name.setTextContent("Alp");
			player.appendChild(id);
			player.appendChild(name);
			game.appendChild(player);
			// ------------------
			// Creating <board>
			Node board = document.createElement("board");
			game.appendChild(board);
			Node lokums = document.createElement("lokums");
			board.appendChild(lokums);
			Node obstacles = document.createElement("obstacles");
			board.appendChild(obstacles);
			Lokum[][] lokumList = GameBoard.getInstance().getLokumList();
			for (int i = 0; i < lokumList.length; i++) {
				for (int j = 0; j < lokumList[i].length; j++) {
					Lokum lokum = lokumList[i][j];
					// if (isTimedLokum)
					// timeLeft == TimedLokum.getTime();
					// else timeLeft == -1

					if (lokum instanceof obstacle) {
						Element obstacleElement = document
								.createElement("obstacle");
						obstacles.appendChild(obstacleElement);

						Element colorElement = document.createElement("color");
						colorElement.appendChild(document
								.createTextNode("black"));
						obstacleElement.appendChild(colorElement);

						Element positionElement = document
								.createElement("position");
						Element xcoord = document.createElement("xcoord");
						xcoord.appendChild(document.createTextNode(String
								.valueOf(i)));
						positionElement.appendChild(xcoord);
						Element ycoord = document.createElement("ycoord");
						ycoord.appendChild(document.createTextNode(String
								.valueOf(j)));
						positionElement.appendChild(ycoord);
						obstacleElement.appendChild(positionElement);
					} else {
						Element lokumElement = document.createElement("lokum");
						lokums.appendChild(lokumElement);

						Element colorElement = document.createElement("color");
						Color c = lokum.getColor();
						if (c.equals(Lokum.RED))
							colorElement.appendChild(document
									.createTextNode("red"));
						if (c.equals(Lokum.YELLOW))
							colorElement.appendChild(document
									.createTextNode("yellow"));
						if (c.equals(Lokum.GREEN))
							colorElement.appendChild(document
									.createTextNode("green"));
						if (c.equals(Lokum.BROWN))
							colorElement.appendChild(document
									.createTextNode("brown"));
						lokumElement.appendChild(colorElement);

						Element typeElement = document.createElement("type");

						Element positionElement = document
								.createElement("position");
						Element xcoord = document.createElement("xcoord");
						xcoord.appendChild(document.createTextNode(String
								.valueOf(i)));
						positionElement.appendChild(xcoord);
						Element ycoord = document.createElement("ycoord");
						ycoord.appendChild(document.createTextNode(String
								.valueOf(j)));
						positionElement.appendChild(ycoord);
						lokumElement.appendChild(positionElement);

						Text typeText = document.createTextNode("Lokum");
						if (lokum instanceof colorBomb)
							typeText = document.createTextNode("colorBomb");
						if (lokum instanceof striped
								&& ((striped) lokum).getOrientation() == striped.HORIZONTAL)
							typeText = document.createTextNode("hStriped");
						if (lokum instanceof striped
								&& ((striped) lokum).getOrientation() == striped.VERTICAL)
							typeText = document.createTextNode("vStriped");
						if (lokum instanceof wrapped)
							typeText = document.createTextNode("wrapped");
						if (lokum instanceof TimedLokum)
							typeText = document.createTextNode("tLokum");
						typeElement.appendChild(typeText);
						lokumElement.appendChild(typeElement);
					}
				}
			}
			// ------------------
			// Creating numeric info
			Node goalscoreNode = document.createElement("goalscore");
			goalscoreNode.setTextContent(goalscore);
			game.appendChild(goalscoreNode);
			Node currentscoreNode = document.createElement("currentscore");
			currentscoreNode.setTextContent(currentscore);
			game.appendChild(currentscoreNode);
			Node movesleftNode = document.createElement("movesleft");
			movesleftNode.setTextContent(movesleft);
			game.appendChild(movesleftNode);
			Node timeleftNode = document.createElement("timeleft");
			timeleftNode.setTextContent(timeLeft);
			game.appendChild(timeleftNode);
			Node levelNode = document.createElement("level");
			levelNode.setTextContent(levelid);
			game.appendChild(levelNode);
			// ------------------

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		} catch (TransformerException tfe) {

			tfe.printStackTrace();

		}
	}

	public static Level[] getLevelList() {

		String XML = "levellist.xml";
		String XSD = "levellist.xsd";
		Level[] levellist = null;

		if (validateXMLSchema(XSD, XML)) {

			try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory
						.newDocumentBuilder();
				Document doc = docBuilder.parse(new File("levellist.xml"));

				doc.getDocumentElement().normalize();

				NodeList listOflevels = doc.getElementsByTagName("level");
				int levelCount = listOflevels.getLength();

				levellist = new Level[levelCount];

				for (int s = 0; s < listOflevels.getLength(); s++) {

					int moveCount = 0;
					int timeLimit = 0;
					int highScore = 0;
					Lokum[][] initialBoard = null;
					int scoreNeeded = 0;
					int levelID = 0;
					boolean locked = false;
					int xMax = 0;
					int yMax = 0;

					Node levelNode = listOflevels.item(s);
					if (levelNode.getNodeType() == Node.ELEMENT_NODE) {

						Element levelElement = (Element) levelNode;
						levelID = Integer.parseInt(levelElement
								.getElementsByTagName("levelid").item(0)
								.getTextContent());
						moveCount = Integer.parseInt(levelElement
								.getElementsByTagName("moves").item(0)
								.getTextContent());

						highScore = Integer.parseInt(levelElement
								.getElementsByTagName("highscore").item(0)
								.getTextContent());
						scoreNeeded = Integer.parseInt(levelElement
								.getElementsByTagName("scoreneeded").item(0)
								.getTextContent());
						timeLimit = Integer.parseInt(levelElement
								.getElementsByTagName("timelimit").item(0)
								.getTextContent());
						locked = Boolean.parseBoolean(levelElement
								.getElementsByTagName("locked").item(0)
								.getTextContent());
						xMax = Integer.parseInt(levelElement
								.getElementsByTagName("xcoordnumber").item(0)
								.getTextContent());
						yMax = Integer.parseInt(levelElement
								.getElementsByTagName("ycoordnumber").item(0)
								.getTextContent());

						initialBoard = new Lokum[xMax][yMax];

						NodeList lokumlist = ((Element) levelElement
								.getElementsByTagName("initialboard").item(0))
								.getElementsByTagName("lokum");

						for (int i = 0; i < lokumlist.getLength(); i++) {
							Node lokumNode = lokumlist.item(i);
							Element lokumElement = (Element) lokumNode;
							String colorStr = lokumElement
									.getElementsByTagName("color").item(0)
									.getTextContent();
							int x = Integer.parseInt(((Element) lokumElement
									.getElementsByTagName("position").item(0))
									.getElementsByTagName("xcoord").item(0)
									.getTextContent());
							int y = Integer.parseInt(((Element) lokumElement
									.getElementsByTagName("position").item(0))
									.getElementsByTagName("ycoord").item(0)
									.getTextContent());
							String type = lokumElement
									.getElementsByTagName("type").item(0)
									.getTextContent();
							Color c = null;
							if (colorStr.equals("red"))
								c = Lokum.RED;
							if (colorStr.equals("yellow"))
								c = Lokum.YELLOW;
							if (colorStr.equals("green"))
								c = Lokum.GREEN;
							if (colorStr.equals("brown"))
								c = Lokum.BROWN;
							if (type.equals("Lokum"))
								initialBoard[x][y] = new NormalLokum(c);
							if (type.equals("vStriped"))
								initialBoard[x][y] = new striped(c,
										striped.VERTICAL);
							if (type.equals("hStriped"))
								initialBoard[x][y] = new striped(c,
										striped.HORIZONTAL);
							if (type.equals("wrapped"))
								initialBoard[x][y] = new wrapped(c);
							if (type.equals("colorBomb"))
								initialBoard[x][y] = new colorBomb();
						}

					}
					if (timeLimit == -1) {
						levellist[s] = new NormalLevel(moveCount, highScore,
								initialBoard, scoreNeeded, levelID, locked);
					} else {
						levellist[s] = new TimedLevel(moveCount, highScore,
								initialBoard, scoreNeeded, levelID, locked,
								timeLimit);
					}
				}
			} catch (SAXParseException err) {
				System.out.println("** Parsing error" + ", line "
						+ err.getLineNumber() + ", uri " + err.getSystemId());
				System.out.println(" " + err.getMessage());

			} catch (SAXException e) {
				Exception x = e.getException();
				((x == null) ? e : x).printStackTrace();

			} catch (Throwable t) {
				t.printStackTrace();
			}

		}
		return levellist;
	}

	public static void updateHighScore(Level l) {
		String ID = String.valueOf(l.getLevelID());
		String score = String.valueOf(l.getHighScore());

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("levellist.xml"));

			doc.getDocumentElement().normalize();

			NodeList listOflevels = doc.getElementsByTagName("level");
			for (int s = 0; s < listOflevels.getLength(); s++) {
				Node levelNode = listOflevels.item(s);
				if (levelNode.getNodeType() == Node.ELEMENT_NODE) {

					Element levelElement = (Element) levelNode;
					// Check if node is the level we want to change
					if (ID.equals(levelElement.getElementsByTagName("levelid")
							.item(0).getTextContent())) {

						levelElement.getElementsByTagName("highscore").item(0)
								.setTextContent(score);
					}
				}
			}

			// SAVING
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("levellist.xml"));
			transformer.transform(source, result);

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void updateLocked(Level l) {
		String ID = String.valueOf(l.getLevelID());
		String locked = String.valueOf(l.getLocked());

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("levellist.xml"));

			doc.getDocumentElement().normalize();

			NodeList listOflevels = doc.getElementsByTagName("level");
			for (int s = 0; s < listOflevels.getLength(); s++) {
				Node levelNode = listOflevels.item(s);
				if (levelNode.getNodeType() == Node.ELEMENT_NODE) {

					Element levelElement = (Element) levelNode;
					// Check if node is the level we want to change
					if (ID.equals(levelElement.getElementsByTagName("levelid")
							.item(0).getTextContent())) {

						levelElement.getElementsByTagName("locked").item(0)
								.setTextContent(locked);
					}
				}
			}

			// SAVING
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("levellist.xml"));
			transformer.transform(source, result);

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// XMLValidation

	static boolean validateXMLSchema(String string, String string2) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(string));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(string2)));
		} catch (final Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	class XMLValidation {

		public boolean validateXMLSchema(String xsdPath, String xmlPath) {

			try {
				SchemaFactory factory = SchemaFactory
						.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = factory.newSchema(new File(xsdPath));
				Validator validator = schema.newValidator();
				validator.validate(new StreamSource(new File(xmlPath)));
			} catch (final Exception e) {
				System.out.println("Exception: " + e.getMessage());
				return false;
			}
			return true;
		}
	}

}
