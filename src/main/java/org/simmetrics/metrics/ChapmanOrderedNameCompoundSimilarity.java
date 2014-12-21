/*
 * SimMetrics - SimMetrics is a java library of Similarity or Distance
 * Metrics, e.g. Levenshtein Distance, that provide float based similarity
 * measures between String Data. All metrics return consistant measures
 * rather than unbounded similarity scores.
 *
 * Copyright (C) 2005 Sam Chapman - Open Source Release v1.1
 *
 * Please Feel free to contact me about this library, I would appreciate
 * knowing quickly what you wish to use it for and any criticisms/comments
 * upon the SimMetric library.
 *
 * email:       s.chapman@dcs.shef.ac.uk
 * www:         http://www.dcs.shef.ac.uk/~sam/
 * www:         http://www.dcs.shef.ac.uk/~sam/stringmetrics.html
 *
 * address:     Sam Chapman,
 *              Department of Computer Science,
 *              University of Sheffield,
 *              Sheffield,
 *              S. Yorks,
 *              S1 4DP
 *              United Kingdom,
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package org.simmetrics.metrics;

import java.util.ArrayList;

import org.simmetrics.SimplyfingStringMetric;
import org.simmetrics.TokenizingStringMetric;
import org.simmetrics.tokenisers.Tokenizer;
import org.simmetrics.tokenisers.WhitespaceTokenizer;

/**
 * ChapmanOrderedNameCompoundSimilarity tests similarity upon the most similar
 * in terms of token based names where the later names are valued higher than
 * earlier names. Surnames are less flexible.
 *
 * This is intended to provide a better rating for lists of proper names.
 *
 *
 * @author Sam Chapman, NLP Group, Sheffield Uni, UK
 * 
 */
public class ChapmanOrderedNameCompoundSimilarity extends
		TokenizingStringMetric {

	private final SimplyfingStringMetric internalStringMetric1 = new Soundex();

	private final SimplyfingStringMetric internalStringMetric2 = new SmithWaterman();

	/**
	 * Constructs a ChapmanOrderedNameCompoundSimilarity metric with a
	 * {@link WhitespaceTokenizer}.
	 */
	public ChapmanOrderedNameCompoundSimilarity() {
		this(new WhitespaceTokenizer());
	}

	/**
	 * Constructs a ChapmanOrderedNameCompoundSimilarity metric with the given
	 * tokenizer.
	 *
	 * @param tokeniser
	 *            tokenizer to use
	 */
	public ChapmanOrderedNameCompoundSimilarity(final Tokenizer tokenizer) {
		super(tokenizer);
	}


	protected final float compareSimplified(final String string1,
			final String string2) {
		// split the strings into tokens for comparison
		final ArrayList<String> str1Tokens = tokenizeToList(string1);
		final ArrayList<String> str2Tokens = tokenizeToList(string2);
		int str1TokenNum = str1Tokens.size();
		int str2TokenNum = str2Tokens.size();
		int minTokens = Math.min(str1TokenNum, str2TokenNum);

		float SKEW_AMMOUNT = 1.0f;

		float sumMatches = 0.0f;
		for (int i = 1; i <= minTokens; i++) {
			float strWeightingAdjustment = ((1.0f / minTokens) + (((((minTokens - i) + 0.5f) - (minTokens / 2.0f)) / minTokens)
					* SKEW_AMMOUNT * (1.0f / minTokens)));
			final String sToken = str1Tokens.get(str1TokenNum - i);
			final String tToken = str2Tokens.get(str2TokenNum - i);

			final float found1 = internalStringMetric1.compare(sToken, tToken);
			final float found2 = internalStringMetric2.compare(sToken, tToken);
			sumMatches += ((0.5f * (found1 + found2)) * strWeightingAdjustment);
		}
		return sumMatches;
	}

}