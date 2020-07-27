using Atlas_frontend.Models;
using Atlas_frontend.Models;
using Atlas_frontend.Services;
using Atlas_frontend.Services.Implementation;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Service
{
    public interface IReponseService: IBaseEntityService<ResponseModel>
    {

       public Task<List<ResponseModel>> GetReponseByEvalAsync(ISession session, long idEvaluation);
        
    }
}
