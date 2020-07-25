using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class SujetService : BaseService<SujetModel>, ISujetService
    {
        public SujetService(IRestAPIClient restAPIClient) : base(restAPIClient, "sujet")
        {
        }

        public Task affecterQuestionToSujetAsync(ISession session, long? idSujet, long? idQuestion)
        {
            throw new NotImplementedException();
        }
    }
}
