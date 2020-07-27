using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IAbsenceService : IBaseEntityService<AbsenceModel>
    {
        public Task<List<AbsenceModel>> GetListAbsenceByStatusForUser(ISession session, string status, long idUser);
        public Task<List<AbsenceModel>> GetListAbsenceByStatusForManager(ISession session, string status, long idManager);
        public Task<List<AbsenceModel>> GetListAbsenceByStatus(ISession session, string status);
        public Task<List<AbsenceModel>> GetListAbsenceForUser(ISession session, long idUser);
        public Task<float> GetSoldeCongee(ISession session, long idUser);
        public Task<float> GetSoldeCongeeTotale(ISession session, long idUser);

    }
}
