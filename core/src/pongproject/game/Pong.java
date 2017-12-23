package pongproject.game;

import java.sql.SQLException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pongproject.game.database.databaseManager;
import pongproject.game.gamescreen.GameScreen;
import pongproject.game.highscorescreen.HighScoreScreen;
import pongproject.game.loginscreen.LoginScreen;
import pongproject.game.menuscreen.MenuScreen;

public class Pong extends Game {
	

	
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private BitmapFont secondFont;
	private BitmapFont loadFont;
	
	
	private Skin skin;
	
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private HighScoreScreen highScoreScreen;
	private LoginScreen loginScreen;
	
	private  databaseManager data;
	
	private boolean firstConnection;
	private boolean loggedIn;
	
	
	/*to do
	 * 
	 * 
	 * Add music for screens. Sounds for button clicks. Sounds for all game events. Paddle hits, wall hits? , goal scores.
	 * 
	 * 
	 * 
	 * Need to redo all fonts, have separate bitMapFonts for each ranking.
	 * Do fonts for titles of screens.
	 * Remember to close all fonts in dispose methods after they are sorted.
	 * Sort out spacing in scores.
	 * Maybe have fonts in each class rather than using them from pongGame if necessary. Maybe used shared for efficiency. See what works.
	 * 
	 * 
	 * 
	 * In all the exceptions where I have just returned to the menuScreen. 
	 * Maybe put a line of code after to set a label or similar on the menuScreen to show that there has been a loss of connection to database.
	 * 
	 * User arial50 for all fonts and labels
	 * 
	 * 
	 * Remember to clear all scores from database before deployment, add dummy scores suitable for game mode of first to 2 or 3.
	 * 
	 * 
	 *
	 * Next do the highscore screen. Add index to the necessary columns in pong_scores. Might be username and scores so far. Username to get the players scores only. Score to get the highest scores? 
	 * 
	 * Might need to change name of paddle velocity variable. I think its speed not velocity since x remains the same.
	 * Menu and buttons are placed on screen Y coordinate with magic number, need to place according to screen size
	 * 
	 * Tested with delta a lot, seemed to make things worse
	 * 
	 * 
	 * Change "Login with a different account" to maybe just "Login" Move to bottom under highscores
	 * 
	 * Add tab function to buttons?
	 * 
	 * Remove unused libraries from build path? Controllers etc
	 * 
	 * 
	 * Idea for later on, allow paddle to move in confined area left and right
	 * Remove Batch.draw in render methods when game is done.
	 * Re do all text with hiero font creator.
	 * 
	 * 
	 * Remove empty pause, resume implementations if possible
	 * 
	 * 
	 * 
	 * If randomly spawned powerups for length of paddle are used. Make use of libgdx timer to set duration of powerup. Random java class to set positioning on the middle x axis.
	 * 
	 * If you do 2 player, try to just use gameScreen class but with a new twoPlayerGameController class. Show player controls differently
	 * 
	 * 
	 * Tweak difficulty and max ball x velocity at the end
	 * 
	 * 
	 * On export "Extract required libraries into generated JAR"
	 * 
	 */
	
	//initialises all necessary variables and sets screen to the menu
	
	@Override
	public void create () {
		
		
		
	
			try {
				data = new databaseManager();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			
		
		Gdx.graphics.setTitle(Constants.title);
		
		camera = new OrthographicCamera();
		
		batch = new SpriteBatch();
		
		loadFont = new BitmapFont(Gdx.files.internal("arial50.fnt"));
		
		
		font = new BitmapFont();
		font.getData().setScale(0.7f);
		
		secondFont = new BitmapFont();
		secondFont.getData().setScale(1f);
		//errorFont.setColor(Color.RED);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		highScoreScreen = new HighScoreScreen(this);
		loginScreen = new LoginScreen(this);
		
	 //testing purposes
		
		loggedIn = false;
		
		this.setScreen(menuScreen);
	}

	//Disposes of resources on game exit
	@Override
	public void dispose() {
		
		super.dispose();
		font.dispose();
		batch.dispose();
		menuScreen.dispose();
		gameScreen.dispose();
		highScoreScreen.dispose();
		loginScreen.dispose();
	
	}
	
	// Getter methods
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public BitmapFont getSecondFont() {
		return secondFont;
	}
	
	public BitmapFont getLoadFont() {
		return loadFont;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public HighScoreScreen getHighScoreScreen() {
		return highScoreScreen;
	}
	
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
	
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}
	

	
	public Skin getSkin() {
		return skin;
	}

	public databaseManager getData() {
		return data;
	}
	
	public boolean getLoggedIn(){
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean getFirstConnection(){
		return firstConnection;
	}
	
	public void setFirstConnection(boolean firstConnection) {
		this.firstConnection = firstConnection;
	}
}
