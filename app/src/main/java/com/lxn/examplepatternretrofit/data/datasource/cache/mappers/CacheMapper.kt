package com.lxn.examplepatternretrofit.data.datasource.cache.mappers

import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.data.model.EntityMapper
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MatchCacheEntity
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<MatchCacheEntity, Match> {
    
    override fun mapFromEntity(entity: MatchCacheEntity): Match {
        return Match(
            id = entity.id,
            doi1 = entity.doi1,
            doi2 = entity.doi2,
            gio = entity.gio,
            kenh = entity.kenh,
            ngay = entity.ngay,
            quocky1 = entity.quocky1,
            quocky2 = entity.quocky2,
            vong = entity.vong
        )
    }


    override fun mapToEntity(domainModel: Match): MatchCacheEntity {
        return MatchCacheEntity(
            id = domainModel.id,
            doi1 = domainModel.doi1,
            doi2 = domainModel.doi2,
            gio = domainModel.gio,
            kenh = domainModel.kenh,
            ngay = domainModel.ngay,
            quocky1 = domainModel.quocky1,
            quocky2 = domainModel.quocky2,
            vong = domainModel.vong
        )
    }

    fun mapFromEntityList(listEntity: List<MatchCacheEntity>): List<Match> {
        return listEntity.map {
            mapFromEntity(it)
        }
    }
}