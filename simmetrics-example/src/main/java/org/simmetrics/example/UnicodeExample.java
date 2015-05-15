package org.simmetrics.example;

import static org.simmetrics.StringMetricBuilder.with;

import java.util.Locale;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.simplifiers.Case;
import org.simmetrics.simplifiers.NonDiacritics;
import org.simmetrics.simplifiers.Normalizer;
import org.simmetrics.simplifiers.UnicodeConfusables;
import org.simmetrics.simplifiers.WordCharacters;
import org.simmetrics.tokenizers.QGram;
import org.simmetrics.tokenizers.Whitespace;

public class UnicodeExample {

	public static void main(String[] args) {

		StringMetric metric = with(new CosineSimilarity<String>())
				.simplify(new UnicodeConfusables())
				.simplify(new NonDiacritics())
				.simplify(new Case.Lower(Locale.ENGLISH))
				.tokenize(new Whitespace())
				.tokenize(new QGram(2)).build();


		System.out.println(metric.compare("paypal", "paypal")); // 1.00
		
		// Skeleton representations of unicode strings containing
		// confusable characters are equal
		System.out.println(metric.compare("paypal", "𝔭𝒶ỿ𝕡𝕒ℓ")); // 1.00
		System.out.println(metric.compare("paypal", "ρ⍺у𝓅𝒂ן"));  
		System.out.println(metric.compare("ρ⍺у𝓅𝒂ן", "𝔭𝒶ỿ𝕡𝕒ℓ")); // 1.00
		System.out.println(metric.compare("paypal", "paypal")); // 1.00

		// The case simplifier removes case
		System.out.println(metric.compare("payPal", "paypal")); // 1.00
		// Not affected by non-diacritical characters
		System.out.println(metric.compare("payPal", "pàỳpąl")); // 1.00

	}

}
