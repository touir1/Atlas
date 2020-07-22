using Atlas_frontend.Models;
using Atlas_frontend.Utils.RestAPI;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Services.Implementation
{
    public class ProjetService : BaseService<ProjetModel>, IProjetService
    {
        public ProjetService(IRestAPIClient restAPIClient) : base(restAPIClient, "projet")
        {
        }

        public async Task AffecterUserToProjetAsync(ISession session, long? idProjet, long? idUser)
        {
            var result = await _client.PutAsync<Object, Object>(session, $"projet/Affecter/{idProjet}/{idUser}", null);
        }

        public async Task CloturerProjet(ISession session, long? idProjet)
        {
            var result = await _client.PutAsync<Object, Object>(session, $"projet/cloturer/{idProjet}", null);
        }

        public async Task<List<ProjetModel>> GetListProjectByManager(ISession session, long? idManager)
        {
            RestApiResponse<List<ProjetModel>> result = await _client.GetAsync<List<ProjetModel>>(session, $"projet/byManager/{idManager}");
            return result.Result;
        }
        public async Task<List<UserModel>> GetListUserByProjectAsync(ISession session, long idProjet)
        {
            var result = await _client.GetAsync<List<UserModel>>(session, $"projet/Members/{idProjet}");
            return result.Result;
        }

        public async Task RemoveAllUserFromProjetAsync(ISession session, long? idProjet)
        {
            var result = await _client.PutAsync<Object, Object>(session, $"projet/removeUsers/{idProjet}", null);
        }
    }
}
