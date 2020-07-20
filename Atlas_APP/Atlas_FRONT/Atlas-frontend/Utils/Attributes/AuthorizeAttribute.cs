using Atlas_frontend.Models.Enums;
using Atlas_frontend.Utils.Filters;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Attributes
{
    public class AuthorizeAttribute : TypeFilterAttribute
    {
        public AuthorizeAttribute(RankEnum[] authorizedRanks):
            base(typeof(AuthorizeActionFilter))
        {
            Arguments = new object[] { authorizedRanks };
        }
    }
}
