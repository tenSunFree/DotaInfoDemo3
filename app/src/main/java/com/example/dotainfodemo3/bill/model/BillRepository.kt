package com.example.dotainfodemo3.bill.model

import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean
import com.example.dotainfodemo3.hero_datasource.local.bill.BillLocal
import com.example.dotainfodemo3.hero_datasource.remote.bill.BillRemote
import com.example.dotainfodemo3.common.model.DataState
import com.example.dotainfodemo3.common.model.LoadingState
import com.example.dotainfodemo3.common.model.QueueState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BillRepository(
    private val local: BillLocal,
    private val remote: BillRemote,
) {

    fun getPhotos(): Flow<DataState<List<BillPhotoBean>>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            val remoteBeanList: List<BillPhotoBean> = try {
                remote.getPhotos()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Error(
                        queueState = QueueState(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown error",
                        )
                    )
                )
                listOf()
            }
            local.insertBillPhotoAll(remoteBeanList)
            val localBeanList = local.selectBillPhotoAll()
            emit(DataState.Success(localBeanList))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Error<List<BillPhotoBean>>(
                    queueState = QueueState(
                        title = "Error",
                        description = e.message ?: "Unknown error",
                    )
                )
            )
        } finally {
            emit(DataState.Loading(loadingState = LoadingState.Idle))
        }
    }
}




