package sk.exceptional.spongia14.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.Replica;
import sk.tomsik68.resourceslib.Resources;

public class DialogWizard {
    private Dialog dialog;
    private Replica currentReplica;
    private int currentReplicaId;
    private Resources resourcesInstance;
    private Image bubble;
    private boolean done;
    private static boolean wasEnter = false;

    public DialogWizard(Dialog dialog) {
	this.dialog = dialog;
	currentReplicaId = 0;
    }

    public void init(Resources res) {
	resourcesInstance = res;
	bubble = res.getImage("gui.dlg_bubble");
	switchReplica(currentReplicaId);
    }

    private void switchReplica(int currentReplicaId2) {
	this.currentReplicaId = currentReplicaId2;
	currentReplica = dialog.getReplicas().get(currentReplicaId);
    }

    public void render(Graphics gfx) {
	gfx.drawImage(bubble, 0, 0);
	Image portrait = resourcesInstance.getImage(dialog.getActor(
		currentReplica.getActorId()).getPortraitResource());
	gfx.drawString(currentReplica.getText(), 50, bubble.getHeight() / 2);
	gfx.drawImage(portrait, 0, 0);
    }

    public void update(GameContainer gc) {
	if (gc.getInput().isKeyDown(Input.KEY_ENTER) && !wasEnter) {
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
}
