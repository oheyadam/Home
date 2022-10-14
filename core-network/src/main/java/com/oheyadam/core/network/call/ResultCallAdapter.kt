package com.oheyadam.core.network.call

import com.oheyadam.core.common.network.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter(private val type: Type) : CallAdapter<Type, Call<Result<Type>>> {

  override fun responseType(): Type = type
  override fun adapt(call: Call<Type>): Call<Result<Type>> = ResultCall(call)
}
