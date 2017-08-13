/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.gachi.view.main.fragments.search.adapter

import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival

/**
 * Created by yonghoon on 2017-08-11
 */

interface SearchAdapterContract {
    interface View {
        fun setItemClickListener(listener: OnItemClickListener)
    }

    interface Model {
        fun addItem(item: Festival)
        fun clearItems()
    }
}