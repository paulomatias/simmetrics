package org.simmetrics.simplifiers;

import static com.github.mpkorstanje.unicode.tr39confusables.Skeleton.skeleton;

/**
 * Applies the Skeleton transform from <a
 * href="http://www.unicode.org/reports/tr39">Unicode TR39</a>. This substitutes
 * confusable code points to a specific (combination) of codepoints.
 * 
 * @author mpkorstanje
 *
 */
public class UnicodeConfusables implements Simplifier {

	@Override
	public String simplify(String input) {
		return skeleton(input);
	}

}
