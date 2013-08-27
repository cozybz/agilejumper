package zuijin.jsty.screen;

import java.text.DecimalFormat;
import java.util.List;

import zuijin.jsty.MainActivity;
import zuijin.jsty.actor.Ball;
import zuijin.jsty.actor.Food;
import zuijin.jsty.actor.MoveBee;
import zuijin.jsty.actor.MoveBoard;
import zuijin.jsty.actor.ParticleActor;
import zuijin.jsty.actor.PassBoard;
import zuijin.jsty.actor.RotateBoard;
import zuijin.jsty.actor.SpiderWeb;
import zuijin.jsty.actor.StaticBoard;
import zuijin.jsty.box2d.Box2dManager;
import zuijin.jsty.box2d.UserData;
import zuijin.jsty.dao.GameData;
import zuijin.jsty.utils.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class GameScreen implements Screen {
	private Stage stage;
	private Group actorGroup;
	private Ball ball;
	private InputListener inputListener;
	private ActorGestureListener gestureListener;
	private int off;
	private float height, width, volume, flingvx, flingvy;
	private boolean flingdamp = false;
	private PassBoard passBoard;
	private String passTime;
	private Boolean isPass = false;
	private Boolean isStart = false;
	private Boolean isRun;
	private Label timeLabel;
	private Label levelLabel;
	private String levelTime;
	private double extraTime = 0.00f;
	private boolean flag=true;
	private double time;

	@Override
	public void render(float delta) {
		if(!isPass)
			Box2dManager.step();
		flingDamp();
		ball.applyAccForce();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (isStart)
			time();
		stage.draw();
		pass();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		stage = new Stage(480, 800, false);
		Box2dManager.createWorld();
		System.out.println(off + "π“ø®s");
		List<Ball> balls = GameData.getBall(off);
		List<Food> foods = GameData.getFood(off);
		List<StaticBoard> staticBoards = GameData.getStaticBoard(off);
		List<MoveBoard> MoveBoards = GameData.getMoveBoard(off);
		List<RotateBoard> rotateBoards = GameData.getRotateBoard(off);
		List<SpiderWeb> spiderWebs = GameData.getSpiderWeb(off);
		List<MoveBee> moveBees = GameData.getMoveBee(off);

		flag=true;
		isPass=false;
		isStart = false;
		height = GameData.getMapHeight(off);
		width = GameData.getMapWidth(off);
		volume = GameData.getVolume();
		actorGroup = new Group();
		ball = balls.get(0);
		actorGroup.addActor(ball);

		for (int i = 0; i < foods.size(); i++)
			actorGroup.addActor(foods.get(i));
		for (int i = 0; i < staticBoards.size(); i++)
			actorGroup.addActor(staticBoards.get(i));
		for (int i = 0; i < MoveBoards.size(); i++)
			actorGroup.addActor(MoveBoards.get(i));
		for (int i = 0; i < rotateBoards.size(); i++)
			actorGroup.addActor(rotateBoards.get(i));
		for (int i = 0; i < spiderWebs.size(); i++)
			actorGroup.addActor(spiderWebs.get(i));
		for (int i = 0; i < moveBees.size(); i++)
			actorGroup.addActor(moveBees.get(i));
		
		passBoard = GameData.getPassBoard(off);
		actorGroup.addActor(passBoard);
		Image backgroundImage = new Image(GameData.getBackgroundRegion(off));
		backgroundImage.setFillParent(true);

		stage.addActor(backgroundImage);
		stage.addActor(actorGroup);

		if (volume == 1) {
			Assets.backgroundMusic.setLooping(true);
			Assets.backgroundMusic.setVolume(0.6f);
			Assets.backgroundMusic.play();
		}

		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);

		gestureListener = new ActorGestureListener() {

			@Override
			public void pan(InputEvent event, float x, float y, float deltaX,
					float deltaY) {
				actorGroup.translate(deltaX * 0.6f, deltaY * 0.6f);
			}

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				flingdamp = false;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				flingdamp = true;
			}

			@Override
			public void tap(InputEvent event, float x, float y, int count,
					int button) {
				ball.jump();
			}

		};

		inputListener = new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.BACK) {
					isRun = false;
					MainActivity.game.setScreen(Game.mainMenuScreen);
				}
				return false;
			}
		};

		stage.addListener(gestureListener);
		stage.addListener(inputListener);

		Box2dManager.world.setContactListener(new ContactListener() {

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				final Body bodyA = contact.getFixtureA().getBody();
				final Body bodyB = contact.getFixtureB().getBody();
				final UserData udA = (UserData) bodyA.getUserData();
				final UserData udB = (UserData) bodyB.getUserData();

				if (udA.type == 0 && udB.type == 1) {
					contact.setEnabled(false);
					bodyB.setActive(false);
					if (volume == 1)
						Assets.eatSound.play();
					udA.eatNum++;
					if (udA.eatNum == 3) {
						Image glad = new Image(Assets.glad);
						glad.setX(passBoard.getX() + passBoard.getWidth() / 2
								- glad.getWidth() / 2);
						glad.setY(passBoard.getY() - glad.getHeight() * 0.95f
								+ 3);
						actorGroup.addActor(glad);
						actorGroup.addActor(new ParticleActor(passBoard.getX(),
								passBoard.getY()));
						passBoard.pass();
					}

				} else if (udA.type == 1 && udB.type == 0) {
					contact.setEnabled(false);
					bodyA.setActive(false);
					if (volume == 1)
						Assets.eatSound.play();
					udB.eatNum++;
					if (udB.eatNum == 3) {
						actorGroup.addActor(new ParticleActor(passBoard.getX(),
								passBoard.getY()));
						passBoard.pass();
					}
				}

				if (udA.type == 0 && udB.type == 5) {
					contact.setEnabled(false);
					bodyA.setLinearDamping(3);
					new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							bodyA.setLinearDamping(0);
						}
					}).start();

				} else if (udA.type == 5 && udB.type == 0) {
					contact.setEnabled(false);
					bodyB.setLinearDamping(3);
					new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							bodyB.setLinearDamping(0);
						}
					}).start();
				}
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}

			@Override
			public void endContact(Contact contact) {
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
				UserData udA = (UserData) bodyA.getUserData();
				UserData udB = (UserData) bodyB.getUserData();
				if (udA.type == 0) {
					if (udB.jumpable == true) {
						udA.jumpable = false;
						udB.jumpable = false;
					}
				} else if (udB.type == 0) {
					if (udA.jumpable == true) {
						udA.jumpable = false;
						udB.jumpable = false;
					}
				}

			}

			@Override
			public void beginContact(Contact contact) {
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
				UserData udA = (UserData) bodyA.getUserData();
				UserData udB = (UserData) bodyB.getUserData();

				if (udA.type == 0) {
					if (bodyA.getPosition().y != contact.getWorldManifold()
							.getPoints()[0].y) {
						udA.jumpable = true;
						udB.jumpable = true;
					}
				} else if (udB.type == 0) {
					if (bodyB.getPosition().y != contact.getWorldManifold()
							.getPoints()[0].y) {
						udA.jumpable = true;
						udB.jumpable = true;
					}
				}

			}
		});
		isStart = true;
		levelTime=GameData.getTime(off);
		if(levelTime==null)
			levelTime="0.0";
		timeLabel = new Label("time:0.0s", new Label.LabelStyle(Assets.timeFont, new Color(
				1, 1, 1, 1)));
		levelLabel = new Label("level:"+levelTime+"s", new Label.LabelStyle(Assets.timeFont, new Color(
				1, 1, 1, 1)));
		
	}

	@Override
	public void hide() {
		if (Assets.backgroundMusic.isPlaying())
			Assets.backgroundMusic.stop();

		stage.removeListener(inputListener);
		stage.removeListener(gestureListener);
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(timeLabel!=null)
			timeLabel.remove();
		if(levelLabel!=null)
			levelLabel.remove();
		extraTime = time;
		isRun = false;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		isStart = true;
	}

	@Override
	public void dispose() {
		if (stage != null)
			stage.dispose();
		Box2dManager.dispose();
	}

	public void setOff(int off) {
		this.off = off;
	}

	private void pass() {
		if (ball.getY() >= height) {
			isPass = true;
			isRun = false;
			
			
			if(flag){
				boolean b=false;
				if(levelTime.equals("0.0"))
					b=true;
				else{
					float time1=Float.parseFloat(levelTime);
					float time2=Float.parseFloat(passTime);
					if(time1>time2)
						b=true;
				}
				if(b){
					Gdx.input.getTextInput(new TextInputListener() {

						public void input(String name) {
						    GameData.rankInsert(off, name, passTime);
						}

						public void canceled() {
						
						}
					}, " ‰»Î’À∫≈", "");
				}				
				flag=false;
			}
			
			
			dialog();
			if (off == 8) {
				MainActivity.game.setScreen(Game.mainMenuScreen);
			} else {
				int maxoff = GameData.getOff();
				if (maxoff < off + 1) {
					GameData.saveOff(off + 1);
				}
			}
		}
	}

	private void flingDamp() {
		if (flingdamp) {

			 if (ball.getY() + actorGroup.getY() > 450)
			 flingvy = (450 - ball.getY() - actorGroup.getY()) * 4;
			 if (ball.getY() + actorGroup.getY() < 350)
			 flingvy = (350 - ball.getY() - actorGroup.getY()) * 4;
			
			 if (ball.getX() + actorGroup.getX() > 250)
			 flingvx = (250 - ball.getX() - actorGroup.getX()) * 4;
			 if (ball.getX() + actorGroup.getX() < 150)
			 flingvx = (150 - ball.getX() - actorGroup.getX()) * 4;

			if (flingvy > 50)
				flingvy -= 50;
			else if (flingvy < -50)
				flingvy += 50;
			else
				flingvy = 0;

			if (flingvx > 50)
				flingvx -= 50;
			else if (flingvx < -50)
				flingvx += 50;
			else
				flingvx = 0;

			actorGroup.translate(flingvx * 0.01f, flingvy * 0.01f);

		}
		if (actorGroup.getY() < 800 - height)
			actorGroup.setY(800 - height);
		else if (actorGroup.getY() > 0)
			actorGroup.setY(0);

		if (actorGroup.getX() < 480 - width)
			actorGroup.setX(480 - width);
		else if (actorGroup.getX() > 0)
			actorGroup.setX(0);
	}

	public void dialog() {
		// µØ≥ˆ¥∞ø⁄
		Image dialog1 = new Image(Assets.dialog1);
		Image dialog2 = new Image(Assets.dialog2);
		Label label = new Label(passTime+"s", new Label.LabelStyle(Assets.timeFont,
				new Color(1, 1, 1, 1)));
		dialog1.setPosition((Gdx.graphics.getWidth() - dialog1.getWidth()) / 2,
				(Gdx.graphics.getHeight() - dialog1.getHeight()) / 2);
		dialog2.setPosition(Gdx.graphics.getWidth() - dialog2.getWidth(), -30);
		label.setPosition((Gdx.graphics.getWidth() - label.getWidth()) / 2,
				Gdx.graphics.getHeight() / 2 - label.getHeight() * (0.8f));
		dialog2.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				off++;
				MainActivity.game.setScreen(Game.gameScreen);
			}
		});
		stage.addActor(dialog1);
		stage.addActor(dialog2);
		stage.addActor(label);
	}

	// √Î±Ì
	public void time() {
		
		levelLabel.setX(480-levelLabel.getWidth());
		levelLabel.setY(750);
		timeLabel.setX(0f);
		timeLabel.setY(750f);
		class TimerThread implements Runnable {
			public void run() {
				isRun = true;
				double startTime = System.currentTimeMillis();
				while (isRun) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					time = (System.currentTimeMillis() - startTime);
					String strTime = new DecimalFormat("0.0").format((time + extraTime) / 1000);
					timeLabel.setText("time:"+strTime+"s");
					if (isPass == true)
						passTime = strTime;
				}
			}
		}
		isStart = false;
		new Thread(new TimerThread()).start();
		stage.addActor(timeLabel);
		stage.addActor(levelLabel);
	}
}
