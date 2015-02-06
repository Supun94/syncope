/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.server.logic;

import java.util.List;
import org.apache.syncope.common.lib.mod.AbstractSubjectMod;
import org.apache.syncope.common.lib.to.AbstractSubjectTO;
import org.apache.syncope.server.persistence.api.dao.search.OrderByClause;
import org.apache.syncope.server.persistence.api.dao.search.SearchCond;

public abstract class AbstractSubjectLogic<T extends AbstractSubjectTO, V extends AbstractSubjectMod>
        extends AbstractResourceAssociator<T> {

    public abstract T read(Long key);

    public abstract int count();

    public abstract T update(V attributableMod);

    public abstract T delete(Long key);

    public abstract List<T> list(int page, int size, List<OrderByClause> orderBy);

    public abstract List<T> search(SearchCond searchCondition, int page, int size, List<OrderByClause> orderBy);

    public abstract int searchCount(SearchCond searchCondition);
}