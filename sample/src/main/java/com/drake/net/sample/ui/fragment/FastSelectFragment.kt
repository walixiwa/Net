/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drake.net.sample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drake.net.Get
import com.drake.net.Post
import com.drake.net.sample.R
import com.drake.net.utils.fastest
import com.drake.net.utils.scopeNetLife
import kotlinx.android.synthetic.main.fragment_fast_select.*

class FastSelectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fast_select, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        scopeNetLife {

            // 同时发起四个网络请求
            val deferred = Get<String>("api0") // 错误接口
            val deferred1 = Get<String>("api1") // 错误接口
            val deferred2 = Get<String>("api")
            val deferred3 = Post<String>("api")

            // 只返回最快的请求结果
            tv_fragment.text = fastest(deferred, deferred1, deferred2, deferred3)
        }
    }
}