package sk.wolfi.modelengine;

import org.newdawn.slick.Image;

public class WalkingHumanBodyFactory {
    public static WalkingHumanBody create(Image bodyImage, Image headImage,
	    Image leftArmImage, Image rightArmImage, Image leftLegImage,
	    Image rightLegImage) {
	float c = 0.5f * 0.8f;
	WalkingHumanBody result = new WalkingHumanBody(0, 0, (int) (31 * c),
		(int) (46 * c), bodyImage, new Extremity("head",
			(int) (233 * c), (int) (308 * c), headImage,
			(int) (117 * c), (int) (308 * c), (int) (100 * c),
			(int) (33 * c), false), new Extremity("leftArm",
			(int) (100 * c), (int) (306 * c), leftArmImage,
			(int) (40 * c), (int) (20 * c), (int) (70 * c),
			(int) (102 * c), true), new Extremity("rightArm",
			(int) (100 * c), (int) (306 * c), rightArmImage,
			(int) (40 * c), (int) (20 * c), (int) (210 * c),
			(int) (88 * c), false), new Extremity("leftLeg",
			(int) (148 * c), (int) (318 * c), leftLegImage,
			(int) (70 * c), (int) (20 * c), (int) (34 * c),
			(int) (265 * c), true), new Extremity("rightLeg",
			(int) (128 * c), (int) (329 * c), rightLegImage,
			(int) (70 * c), (int) (20 * c), (int) (122 * c),
			(int) (275 * c), true));
	return result;
    }

}
