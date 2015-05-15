package org.simmetrics.simplifiers;

import static java.text.Normalizer.normalize;
import static java.text.Normalizer.Form.NFD;

import java.text.Normalizer.Form;

/**
 * Normalizes a Unicode string. Required when tokenizing Unicode characters.
 * 
 * @author mpkorstanje
 *
 */
public class Normalizer implements Simplifier {

	private final Form form;

	/**
	 * Constructs a new simplifier that normalizes Unicode strings to the given
	 * form.
	 * 
	 * @param form
	 *            a normalization form
	 */
	public Normalizer(Form form) {
		this.form = form;
	}

	/**
	 * Constructs a new simplifier that normalizes Unicode strings to NFD form.
	 */
	public Normalizer() {
		this(NFD);
	}

	@Override
	public String simplify(String input) {
		return normalize(input, form);
	}

}
