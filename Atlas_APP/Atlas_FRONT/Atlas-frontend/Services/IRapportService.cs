using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IRapportService: IBaseEntityService<RapportModel>
    {
        public Task<List<RapportModel>> GetListrapportByUserAndWeek(ISession session, int? semaine, int? mois, int? annee,long? idUser);
        public Task<List<RapportModel>> GetListAllrapportByUser(ISession session, int? semaine, long? idUser);

        public Task DeleteRapport(ISession session, long? idUser, long? idRubrique, int? semaine, int? annee );
    }
}
