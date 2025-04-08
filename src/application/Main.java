package application;

import javafx.application.Application;

import javafx.animation.TranslateTransition;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.io.*;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class Main extends Application {
	// static PlayersClass P=new PlayersClass();

	@Override

	public void start(Stage primaryStage) throws IOException {
		try {
			BorderPane B1 = new BorderPane();
			GridPane root = new GridPane();
			Image img = new Image("File:p.jpg");
			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Text l1 = new Text("The list(seperats by spaces):");
			l1.setFont(Font.font("Verdana", 18));
			l1.setStyle("-fx-background-color:pink;");
			TextField t1 = new TextField();
			t1.setFont(Font.font("Italic", 15));
			t1.setStyle("-fx-background-color:white;");
			t1.setScaleShape(false);
			t1.setCacheShape(true);
			Button b1 = new Button();
			b1.setTextFill(Color.WHITE);
			b1.setStyle("-fx-background-color:pink;");
			b1.setText("calculate");
			Text l2 = new Text("Maximum possiple amount :");
			l2.setFont(Font.font("Verdana", 20));
			l2.setStyle("-fx-background-color:pink;");
			TextField t2 = new TextField();
			t2.setFont(Font.font("Italic", 15));
			Text l4 = new Text("The coins that give the expected result:");
			l4.setFont(Font.font("Verdana", 18));
			l4.setStyle("-fx-background-color:pink;");
			TextField tx = new TextField();
			tx.setFont(Font.font("Italic", 15));
			tx.setStyle("-fx-background-color:white;");
			// tx.setScaleShape(false);
			tx.setCacheShape(true);
			Button b2 = new Button();
			b2.setTextFill(Color.WHITE);
			b2.setStyle("-fx-background-color:pink;");
			b2.setText("calculate");
			t2.setStyle("-fx-background-color:white;");
			root.setPadding(new Insets(10, 10, 10, 10));
			root.setAlignment(Pos.CENTER);
			root.addRow(0, l1, t1, b1);
			root.addRow(2, l2, t2);
			root.addRow(4, l4, tx, b2);
			root.setHgap(5);
			root.setVgap(5);
			Text l3 = new Text("Dynamic progarmming table :.");
			l3.setFont(Font.font("Verdana", 20));
			l3.setStyle("-fx-background-color:pink;");
			VBox hb = new VBox();

			GridPane gp = new GridPane();
			HBox h = new HBox(10);

			// gridpane for table

//button calculate to fo find the maximum amount the coins and show the table
			b1.setOnAction(e -> {

				try {
					String[] arr = t1.getText().split(" ");

					{

						if ((arr.length) % 2 != 0) {
							t1.setText("try again");

						} else {
//2DA 

							// object
							PlayersClass[][] coinarr = new PlayersClass[arr.length][arr.length];
							// 2nested for loop to give an initial value

							for (int i = 0; i < arr.length; i++) {
								for (int j = 0; j < arr.length; j++) {
									coinarr[i][j] = new PlayersClass(0, 0);
									coinarr[i][j].p1 = 0;
									coinarr[i][j].p2 = 0;
									// ta.setText(Arrays.toString(coinarr[i]).toString());
								}
							}

							//
							// arr of coins for player 2
							/*
							 * int[] player2coins = new int[coinArr.length]; // initialize player 2 coins
							 * arr for (int i = 0; i < coinArr.length; i++) { player2coins[i] = 0; }
							 */

							//

							for (int j = 0; j < arr.length; j++) {

								for (int i = j; i >= 0; i--) {
									// case1 when i equal j then the first player takes the coin and the second

									// player has no choice
									if (i == j) {

										coinarr[i][j].p1 = Integer.parseInt(arr[i]);
										coinarr[i][j].p2 = 0;
										/*
										 * if ((j) % 2 == 0) { player2coins[i] = Integer.parseInt(arr[i]); }
										 */

										gp.add(new Label(coinarr[i][j].p1 + " "), j, i);

									}

									// case2 if there are more than 1 coins,the player1
									// will take the maximum coin
									// and player2 will take the minimum

									else if ((i + 1) == j) {

										coinarr[i][j].p1 = Math.max(Integer.parseInt(arr[i]), Integer.parseInt(arr[j]));
										coinarr[i][j].p2 = Math.min(Integer.parseInt(arr[i]), Integer.parseInt(arr[j]));
										gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
										/*
										 * if (Integer.parseInt(arr[i]) >= Integer.parseInt(arr[j])) { if (i % 2 == 0) {
										 * player2coins[i] = Math.max(Integer.parseInt(arr[i]),
										 * Integer.parseInt(arr[j])); } else if (Integer.parseInt(arr[i]) <
										 * Integer.parseInt(arr[j])) { if (j % 2 == 0) { player2coins[j] =
										 * Math.max(Integer.parseInt(arr[i]), Integer.parseInt(arr[j])); } } }
										 */
									}

									// if there are more than 2 coins they have 4 casses
									else {
										// if the first coin+the minimum coin of (i+1_j)(which is the coin of player2)
										// more than
										// the last coin+minimum(i_j-1)then the maximum coins that player1 will take is
										// first coin+the minimum coin of (i+1_j)
										// and player2 will take maximum of (i+1 _j)(wich os the coin of player1
										// on(i+1_j))

										if (Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2 > Integer.parseInt(arr[j])
												+ coinarr[i][j - 1].p2) {
											coinarr[i][j].p1 = Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2;
											coinarr[i][j].p2 = coinarr[i + 1][j].p1;
											// gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
											/*
											 * if (i % 2 == 0) { player2coins[i] = Integer.parseInt(arr[i]); }
											 */

											// tx.setText(arr[i] + "" + arr[i + 1] + "" + arr[j]);

										}
										// if the first coin+the minimum coin of (i+1_j)(which is the coin of player2)
										// lass than
										// the last coin+minimum(i_j-1)then the maximum coins that player1 will take is
										// last coin+the minimum coin of (i_j-1)
										// and player2 will take maximum of (i _j-1)(wich os the coin of player1
										// on(i_j-1))
										else if (Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2 < Integer
												.parseInt(arr[j]) + coinarr[i][j - 1].p2) {
											coinarr[i][j].p1 = Integer.parseInt(arr[j]) + coinarr[i][j - 1].p2;
											coinarr[i][j].p2 = coinarr[i][j - 1].p1;
											// gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
											/*
											 * if (j % 2 == 0) { player2coins[j] = Integer.parseInt(arr[j]); }
											 */

										} else if ((Integer.parseInt(arr[i])
												+ coinarr[i + 1][j].p2) == (Integer.parseInt(arr[j])
														+ coinarr[i][j - 1].p2)
												&& Integer.parseInt(arr[j]) >= Integer.parseInt(arr[i])) {
											coinarr[i][j].p1 = Integer.parseInt(arr[j]) + coinarr[i][j - 1].p2;
											coinarr[i][j].p2 = coinarr[i + 1][j].p1;

										} else if ((Integer.parseInt(arr[i])
												+ coinarr[i + 1][j].p2) == (Integer.parseInt(arr[j])
														+ coinarr[i][j - 1].p2)
												&& Integer.parseInt(arr[i]) >= Integer.parseInt(arr[j])) {
											coinarr[i][j].p1 = Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2;
											coinarr[i][j].p2 = coinarr[i][j - 1].p1;

										}

										int a = Math.max(coinarr[i][j].p1, coinarr[i][j].p2);

									}
									gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
									// int a = Math.max(coinarr[i][j].p1,coinarr[i][j].p2);
									int a = coinarr[i][j].p1;

									t2.setText(Integer.valueOf(a).toString());

								}
							}
						}
					}
				} catch (Exception ex) {
					ex.getMessage();
				}

			});
			b2.setOnAction(e -> {

				try {
					String[] arr = t1.getText().split(" ");

					{

						if ((arr.length) % 2 != 0) {
							t1.setText("try again");

						} else {
//2DA 
							int[] coinArr = new int[arr.length];
							for (int i = 0; i < arr.length; i++) {
								coinArr[i] = Integer.parseInt(arr[i]);
							}

							PlayersClass[][] coinarr = new PlayersClass[arr.length][arr.length];
//2nested for loop to give an initial value 

							for (int i = 0; i < arr.length; i++) {
								for (int j = 0; j < arr.length; j++) {
									coinarr[i][j] = new PlayersClass(0, 0);
									coinarr[i][j].p1 = 0;
									coinarr[i][j].p2 = 0;
									// ta.setText(Arrays.toString(coinarr[i]).toString());
								}
							}

							//
							// arr of coins for player 2
							int[] player2coins = new int[coinArr.length];
							// initialize player 2 coins arr
							for (int i = 0; i < coinArr.length; i++) {
								player2coins[i] = 0;
							}

							//

							for (int j = 0; j < arr.length; j++) {

								for (int i = j; i >= 0; i--) {
									// case1 when i equal j then the first player takes the coin and the second

									// player has no choice
									if (i == j) {

										coinarr[i][j].p1 = Integer.parseInt(arr[i]);
										coinarr[i][j].p2 = 0;
										if ((j) % 2 == 0) {
											player2coins[i] = Integer.parseInt(arr[i]);
										}

										gp.add(new Label(coinarr[i][j].p1 + " "), j, i);

									}

									// case2 if there are more than 1 coins,the player1
									// will take the maximum coin
									// and player2 will take the minimum

									else if ((i + 1) == j) {

										coinarr[i][j].p1 = Math.max(Integer.parseInt(arr[i]), Integer.parseInt(arr[j]));
										coinarr[i][j].p2 = Math.min(Integer.parseInt(arr[i]), Integer.parseInt(arr[j]));
										gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
										if (Integer.parseInt(arr[i]) >= Integer.parseInt(arr[j])) {
											if (i % 2 == 0) {
												player2coins[i] = Math.max(Integer.parseInt(arr[i]),
														Integer.parseInt(arr[j]));
											} else if (Integer.parseInt(arr[i]) < Integer.parseInt(arr[j])) {
												if (j % 2 == 0) {
													player2coins[j] = Math.max(Integer.parseInt(arr[i]),
															Integer.parseInt(arr[j]));
												}
											}
										}
									}

									// if there are more than 2 coins they have 4 casses
									else {
										// if the first coin+the minimum coin of (i+1_j)(which is the coin of player2)
										// more than
										// the last coin+minimum(i_j-1)then the maximum coins that player1 will take is
										// first coin+the minimum coin of (i+1_j)
										// and player2 will take maximum of (i+1 _j)(wich os the coin of player1
										// on(i+1_j))

										if (Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2 >= Integer.parseInt(arr[j])
												+ coinarr[i][j - 1].p2) {
											coinarr[i][j].p1 = Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2;
											coinarr[i][j].p2 = coinarr[i + 1][j].p1;
											gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
											if (i % 2 == 0) {
												player2coins[i] = Integer.parseInt(arr[i]);
											}

											// tx.setText(arr[i] + "" + arr[i + 1] + "" + arr[j]);

										}
										// if the first coin+the minimum coin of (i+1_j)(which is the coin of player2)
										// lass than
										// the last coin+minimum(i_j-1)then the maximum coins that player1 will take is
										// last coin+the minimum coin of (i_j-1)
										// and player2 will take maximum of (i _j-1)(wich os the coin of player1
										// on(i_j-1))
										else if (Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2 < Integer
												.parseInt(arr[j]) + coinarr[i][j - 1].p2) {
											coinarr[i][j].p1 = Integer.parseInt(arr[j]) + coinarr[i][j - 1].p2;
											coinarr[i][j].p2 = coinarr[i][j - 1].p1;
											gp.add(new Label(coinarr[i][j].p1 + " "), j, i);
											if (j % 2 == 0) {
												player2coins[j] = Integer.parseInt(arr[j]);
											}

										} else if ((Integer.parseInt(arr[i])
												+ coinarr[i + 1][j].p2) == (Integer.parseInt(arr[j])
														+ coinarr[i][j - 1].p2)
												&& Integer.parseInt(arr[j]) >= Integer.parseInt(arr[i])) {
											coinarr[i][j].p1 = Integer.parseInt(arr[j]) + coinarr[i][j - 1].p2;
											coinarr[i][j].p2 = coinarr[i + 1][j].p1;
											// gp.add(new Label(coinarr[i][j].p1 + " "), j, i);

											/*
											 * if (j % 2 == 0) { player2coins[j] = Integer.parseInt(arr[j]); }
											 */

										} else if ((Integer.parseInt(arr[i])
												+ coinarr[i + 1][j].p2) == (Integer.parseInt(arr[j])
														+ coinarr[i][j - 1].p2)
												&& Integer.parseInt(arr[i]) >= Integer.parseInt(arr[j])) {
											coinarr[i][j].p1 = Integer.parseInt(arr[i]) + coinarr[i + 1][j].p2;
											coinarr[i][j].p2 = coinarr[i][j - 1].p1;

										}
										int arrPlayer1Coins[] = new int[arr.length];
										for (int k = 0; k < arr.length; k++) {

											arrPlayer1Coins[k] = 0;
											if (player2coins[k] == 0) {

												arrPlayer1Coins[k] = Integer.parseInt(arr[k]);
											}

										}
										int sumPlayer1 = 0;
										int sumPlayer2 = 0;
										String s = "";
										for (int k = 0; k < arr.length; k++) {

											sumPlayer1 += arrPlayer1Coins[k];
											sumPlayer2 += player2coins[k];
											System.out.println(arrPlayer1Coins[k]);
										}

										for (int m = 0; m < arr.length; m++) {
											if (sumPlayer1 >= sumPlayer2) {
												System.out.println(arrPlayer1Coins[m]);
												if (arrPlayer1Coins[m] != 0)
													s += arrPlayer1Coins[m] + " ";
											} else if (sumPlayer1 < sumPlayer2) {
												System.out.println(player2coins[m]);
												// tx.setText(Integer.valueOf(player2coins[m]).toString());
												if (player2coins[m] != 0)
													s += player2coins[m] + " ";
											}

											tx.setText(s);

										}
										// HBox h=new HBox(10);
										// coinArr=new int[arr.length];

									}

								}

							}

						}
						for (int k = 0; k < arr.length; k++) {
							// coinArr[k]=Integer.parseInt(arr[k].trim());
							Circle cir = new Circle(10, 100, 10);
							cir.setFill(Color.RED);
							cir.setStroke(Color.BLACK);
							Text t = new Text(arr[k] + "");
							StackPane stack = new StackPane();
							stack.getChildren().addAll(cir, t);
							h.getChildren().add(stack);
							if (k % 2 == 0) {
								TranslateTransition tr = new TranslateTransition();
								tr.setNode(stack);
								tr.setToY(50);
								tr.play();

							} else {
								/*
								 * TranslateTransition tra = new TranslateTransition(Duration.seconds(1),
								 * stack);
								 */
								TranslateTransition tra = new TranslateTransition();
								tra.setToX(10);
								tra.setNode(stack);
								tra.play();
							}

						}
					}
				} catch (Exception ex) {
					ex.getMessage();
				}

			});
			gp.setStyle("-fx-background-color:pink;");
			hb.getChildren().addAll(l3, gp);
			root.addRow(7, hb);
			root.setHgap(5);
			root.setVgap(5);
			B1.setCenter(root);
			B1.setTop(h);
			Scene scene = new Scene(B1, 400, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setFullScreen(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public String toString() {
		return "Main [getHostServices()=" + getHostServices() + ", getParameters()=" + getParameters() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
