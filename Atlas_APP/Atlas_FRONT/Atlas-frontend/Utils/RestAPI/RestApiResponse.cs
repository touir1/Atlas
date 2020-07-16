using Microsoft.AspNetCore.Diagnostics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.RestAPI
{
    public class RestApiResponse<TResponseEntity>
    {
        public TResponseEntity Result { get; set; }
        public bool IsSuccess { get; set; }
        public HttpStatusCode StatusCode { get; set; }
    }
}
