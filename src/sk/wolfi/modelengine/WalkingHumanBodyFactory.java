package sk.wolfi.modelengine;

import sk.tomsik68.resourceslib.Resources;

public class WalkingHumanBodyFactory {
    public static WalkingHumanBody create(Resources res, String bodyRes,
	    String headRes, String lArmRes, String rArmRes, String rightLegRes,
	    String leftLegRes) {
	Extremity[] extremities = new Extremity[] { new Extremity("head", 233,
		308, res.getImage(headRes), 117, 308, 100, 33, false), };
	WalkingHumanBody result = new WalkingHumanBody(0, 0, 0, 0, bodyRes,
		extremities);
	return result;
    }

}
