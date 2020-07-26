using Atlas_frontend.Models;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services
{
    public interface IEvaluationService : IBaseEntityService<EvaluationModel>
    {

        public Task AffecterUserToEvaluationAsync(ISession session, long? idEvaluation, long? idUser);

        public Task<List<UserModel>> GetListUserByEvalAsync(ISession session, long idEvaluation);
        public Task<List<SujetModel>> GetListSujetByEvalAsync(ISession session, long idEvaluation);

        public Task<List<EvaluationModel>> GetListEvalByUser(ISession session, long idUser);

    }
}
