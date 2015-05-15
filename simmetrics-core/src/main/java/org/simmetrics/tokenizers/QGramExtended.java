/*
 * SimMetrics - SimMetrics is a java library of Similarity or Distance Metrics,
 * e.g. Levenshtein Distance, that provide float based similarity measures
 * between String Data. All metrics return consistent measures rather than
 * unbounded similarity scores.
 * 
 * Copyright (C) 2014 SimMetrics authors
 * 
 * This file is part of SimMetrics. This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * SimMetrics. If not, see <http://www.gnu.org/licenses/>.
 */
package org.simmetrics.tokenizers;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.repeat;
import static java.util.Collections.emptyList;

import java.util.List;
import java.util.Locale;

import org.simmetrics.simplifiers.Normalizer;

/**
 * Basic Q-Gram tokenizer for a variable Q.The Q-Gram is extended beyond the
 * length of the string with padding.
 * <p>
 * Note: a Q-Gram consists of q code points. When working with non-ASCII
 * characters, similar strings should be in the same a normalized form to
 * produce similar tokens.
 * <p>
 * This class is immutable and thread-safe.
 * 
 * @see Normalizer
 */
public class QGramExtended extends AbstractTokenizer {

	private final static String DEFAULT_START_PADDING = "#";
	private final static String DEFAULT_END_PADDING = "#";

	private final String endPadding;
	private final String startPadding;

	private final QGram tokenizer;

	/**
	 * Constructs a q-gram tokenizer with the given q, default padding and
	 * default locale.
	 * 
	 * @param q
	 *            size of the tokens
	 */
	public QGramExtended(int q) {
		this(q, Locale.getDefault());
	}

	/**
	 * Constructs a q-gram tokenizer with the given q, default padding and the
	 * given locale.
	 * 
	 * @param q
	 *            size of the tokens
	 * @param locale
	 *            locale used to determine grapheme boundaries
	 */
	public QGramExtended(int q, Locale locale) {
		this(q, DEFAULT_START_PADDING, DEFAULT_END_PADDING, locale);
	}

	/**
	 * Constructs a q-gram tokenizer with the given q, padding and default
	 * locale.
	 * 
	 * @param q
	 *            size of the tokens
	 * @param startPadding
	 *            padding to apply at the start of short tokens
	 * @param endPadding
	 *            padding to apply at the end of short tokens
	 */
	public QGramExtended(int q, String startPadding, String endPadding) {
		this(q, startPadding, endPadding, Locale.getDefault());
	}

	/**
	 * Constructs a q-gram tokenizer with the given q, padding and locale.
	 * 
	 * @param q
	 *            size of the tokens
	 * @param startPadding
	 *            padding to apply at the start of short tokens
	 * @param endPadding
	 *            padding to apply at the end of short tokens
	 * @param locale
	 *            locale used to determine grapheme boundaries
	 */
	public QGramExtended(int q, String startPadding, String endPadding,
			Locale locale) {
		checkArgument(!startPadding.isEmpty(), "startPadding may not be empty");
		checkArgument(!endPadding.isEmpty(), "endPadding may not be empty");

		this.tokenizer = new QGram(q, locale);
		this.startPadding = repeat(startPadding, q - 1);
		this.endPadding = repeat(endPadding, q - 1);
	}

	/**
	 * Returns the start padding.
	 * 
	 * @return the start padding
	 */
	public String getStartPadding() {
		return startPadding;
	}

	/**
	 * Returns the end padding.
	 * 
	 * @return the end padding
	 */
	public String getEndPadding() {
		return endPadding;
	}

	@Override
	public List<String> tokenizeToList(String input) {
		if (input.isEmpty()) {
			return emptyList();
		}

		return tokenizer.tokenizeToList(startPadding + input + endPadding);
	}

	@Override
	public String toString() {
		return "QGramExtendedTokenizer [startPadding=" + startPadding
				+ ", endPadding=" + endPadding + ", q=" + tokenizer.getQ()
				+ "]";
	}

}
