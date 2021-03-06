/*
 * Copyright 2012 Jakob Külzer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.om.core.api.annotation;

/**
 * Defines how a {@link Property} or {@link Collection} should be retrieved.
 * 
 * @author Jakob Külzer
 * 
 */
public @interface FetchType {

	public enum Strategy {
		Eager, Lazy
	}

	public Strategy value() default Strategy.Eager;

}
