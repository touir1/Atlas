using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class ResponseModel
    {
        [JsonProperty("note")]
        public float Note { get; set; }
        [JsonProperty("reponseText")]
        public String ReponseText { get; set; }
        [JsonProperty("reponseNumeric")]
        public String ReponseNumeric { get; set; }
        [JsonProperty("noteSuperior")]
        public float NoteSuperior { get; set; }
        [JsonProperty("commentaire")]
        public String Commentaire { get; set; }
        [JsonProperty("evaluation")]
        public EvaluationModel Evaluation { get; set; }
        [JsonProperty("question")]
        public QuestionModel Question { get; set; }

    }
}
