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
package org.simmetrics.metrics;

import org.simmetrics.ListMetric;
import org.simmetrics.SetMetric;
import org.simmetrics.StringMetric;

public abstract class Equality {

	public static class String implements StringMetric {

		@Override
		public float compare(java.lang.String a, java.lang.String b) {
			return a.equals(b) ? 1 : 0;
		}
	}

	public static class List<T> implements ListMetric<T> {

		@Override
		public float compare(java.util.List<T> a, java.util.List<T> b) {
			return a.equals(b) ? 1 : 0;
		}
	}

	public static class Set<T> implements SetMetric<T> {

		@Override
		public float compare(java.util.Set<T> a, java.util.Set<T> b) {
			return a.equals(b) ? 1 : 0;
		}
	}
}
