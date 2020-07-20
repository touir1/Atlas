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

        public async Task AffecterUserToProjetAsync(ISession session, long  idProjet,long idUser)
        {
            var result = await _client.PutAsync<Object,Object>(session, $"projet/Affecter/{idProjet}/{idUser}",null);
            
        }
    }
}
