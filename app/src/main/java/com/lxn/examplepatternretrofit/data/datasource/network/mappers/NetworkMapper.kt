package com.lxn.examplepatternretrofit.data.datasource.network.mappers


import com.lxn.examplepatternretrofit.data.model.EntityMapper
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.data.datasource.network.model.MatchObjectNetwork
import javax.inject.Inject

class NetworkMapper @Inject constructor() :
    EntityMapper<MatchObjectNetwork, Match> {
    override fun mapFromEntity(entity: MatchObjectNetwork): Match {
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

    override fun mapToEntity(domainModel: Match): MatchObjectNetwork {
        return MatchObjectNetwork(
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

    fun mapFromEntityList(entities: List<MatchObjectNetwork>): List<Match> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}