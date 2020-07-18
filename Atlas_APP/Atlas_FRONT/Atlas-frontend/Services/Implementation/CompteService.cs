using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class CompteService : BaseService<CompteModel>, ICompteService
    {
        public CompteService(IRestAPIClient restAPIClient) : base(restAPIClient,"compte")
        {
        }

        public async Task<CompteModel> LoginAsync(ISession session, string username, string password)
        {
            RestApiResponse<CompteModel> cpt = await _client.PostAsync<CompteModel, CompteModel>(session , $"{_baseServiceUrl}/login", new CompteModel
            {
                Username = username,
                Password = password
            });

            session.SetString(RestAPIClient.TokenKey, cpt.Result.Token);

            return cpt.Result;
        }
    }
}
