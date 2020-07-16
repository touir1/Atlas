using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class FormationModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("libelle")]
        public String Libelle { get; set; }

    }
}
