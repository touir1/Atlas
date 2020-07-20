using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class CompteService : BaseService<CompteModel>, ICompteService
    {
        private readonly string CompteSessionKey = "_compte";
        public CompteService(IRestAPIClient restAPIClient) : base(restAPIClient,"compte")
        {
        }

        public async Task<CompteModel> LoginAsync(ISession session, string username, string password)
        {
            RestApiResponse<CompteModel> response = await _client.PostAsync<CompteModel, CompteModel>(session , $"{_baseServiceUrl}/login", new CompteModel
            {
                Username = username,
                Password = password
            });

            session.SetString(RestAPIClient.TokenKey, response.Result.Token);
            session.SetString(CompteSessionKey, JsonConvert.SerializeObject(response.Result));

            return response.Result;
        }

        public async Task<Boolean> LogoutAsync(ISession session)
        {
            await _client.PostAsync<object, Boolean>(session, $"{_baseServiceUrl}/logout", null);
            session.Remove(RestAPIClient.TokenKey);
            session.Remove(CompteSessionKey);
            return true;
        }

        public CompteModel GetConnectedCompte(ISession session)
        {
            try
            {
                return (CompteModel)JsonConvert.DeserializeObject(session.GetString(CompteSessionKey),typeof(CompteModel)) ;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public Boolean IsConnected(ISession session)
        {
            try
            {
                CompteModel compte = GetConnectedCompte(session);
                string token = session.GetString(RestAPIClient.TokenKey);
                if(token != null && compte != null && token.Trim() != ""){
                    return true;
                }
                return false;
            }
            catch(Exception)
            {
                return false;
            }
        }
    }
}
