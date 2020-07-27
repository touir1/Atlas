using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ReponsePass
    {

        [JsonProperty("questions")]
        public QuestionModel questions { get; set; }

        [JsonProperty("sujets")]
        public SujetModel sujets { get; set; }

        [JsonProperty("choix")]
        public List<ChoixModel> choix { get; set; }

        [JsonProperty("evaluations")]
        public EvaluationModel evals { get; set; }
    }
}
