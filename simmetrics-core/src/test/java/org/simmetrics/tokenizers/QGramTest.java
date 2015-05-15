package org.simmetrics.tokenizers;

import static java.text.Normalizer.normalize;
import static java.text.Normalizer.Form.NFD;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class QGramTest {

	private QGram qgram = new QGram(2);
	private String ay = "ӒӒӒ";
	private String ayNormalized = normalize(ay, NFD);

	@Test
	public void normalized() {
		// Different normalization results in different tokens
		assertThat(qgram.tokenizeToList(ay),
				not(equalTo(qgram.tokenizeToList(ayNormalized))));

		// But after normalization token lists should be equal
		assertThat(normalizedTokens(qgram.tokenizeToList(ay)),
				equalTo(qgram.tokenizeToList(ayNormalized)));

	}

	private static List<String> normalizedTokens(List<String> list) {
		return Lists.transform(list, new Function<String, String>() {

			@Override
			public String apply(String input) {
				return normalize(input, NFD);
			}
		});
	}


}
