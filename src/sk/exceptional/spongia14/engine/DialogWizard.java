package sk.exceptional.spongia14.engine;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;

import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.Replica;
import sk.tomsik68.resourceslib.Resources;

public class DialogWizard {
    private Dialog dialog;
    private Replica currentReplica;
    private int currentReplicaId;
    private Resources resourcesInstance;
    private Animation clickEnter;
    private Image bubble;
    private boolean done;
    private final UnicodeFont dialogFont;
    private List<String> lines;
    private static boolean wasEnter = false;

    public DialogWizard(Dialog dialog) {
	this.dialog = dialog;
	currentReplicaId = 0;
	dialogFont = new UnicodeFont(new Font("Arial", Font.BOLD, 20));
	ColorEffect ce = new ColorEffect();
	dialogFont.getEffects().add(ce);
	ce.setColor(java.awt.Color.black);
	dialogFont.addAsciiGlyphs();
	dialogFont.addGlyphs("ľščťžýáíéóňČďĎ");
	try {
	    dialogFont.loadGlyphs();
	} catch (SlickException e) {
	    e.printStackTrace();
	}
    }

    public void init(Resources res) {
	resourcesInstance = res;
	bubble = res.getImage("gui.dlg_bubble");
	clickEnter = new Animation(new Image[] {
		res.getImage("gui.clickEnter1"),
		res.getImage("gui.clickEnter2") }, 360);
	switchReplica(currentReplicaId);
    }

    private void switchReplica(int currentReplicaId2) {
	this.currentReplicaId = currentReplicaId2;
	currentReplica = dialog.getReplicas().get(currentReplicaId);
	lines = wrapString(currentReplica.getText(), 50);
    }

    public void render(Graphics gfx) {
	gfx.drawImage(bubble, 0, 0);
	Image portrait = resourcesInstance.getImage(dialog.getActor(
		currentReplica.getActorId()).getPortraitResource());
	gfx.setColor(Color.black);
	int y = bubble.getHeight() / 2 - 30;
	for (String line : lines) {
	    dialogFont.drawString(50, y, line, Color.black);
	    y += dialogFont.getLineHeight();
	}
	gfx.setColor(new Color(0, 0, 0, 230));
	gfx.fillRoundRect(-50, -50, portrait.getWidth() + 70,
		portrait.getHeight() + 70, 35);
	gfx.drawImage(portrait, 0, 0);
	gfx.drawAnimation(clickEnter, 400, 500);
    }

    public void update(GameContainer gc) {
	if ((gc.getInput().isKeyDown(Input.KEY_ENTER)) && !wasEnter) {
	    if (currentReplicaId == dialog.getReplicaCount() - 1) {
		done = true;
	    } else
		switchReplica(currentReplicaId + 1);
	    wasEnter = true;
	} else if (!gc.getInput().isKeyDown(Input.KEY_ENTER)) {
	    wasEnter = false;
	}
    }

    public boolean isDone() {
	return done;
    }

    private static final List<String> wrapString(String s, int length) {
	ArrayList<String> result = new ArrayList<String>();
	StringBuilder temp = new StringBuilder();
	int lastDelimPos = 0;

	for (String token : s.split(" ", -1)) {
	    if (temp.length() - lastDelimPos + token.length() > length) {
		result.add(temp.toString());
		temp = new StringBuilder();
		temp.append(token);
		lastDelimPos = temp.length() + 1;
	    } else {
		if (temp.length() != 0) {
		    temp.append(" ");
		}
		temp.append(token);
	    }
	}
	if (temp.length() > 0) {
	    result.add(temp.toString());
	    temp = new StringBuilder();
	}
	return result;
    }
}
