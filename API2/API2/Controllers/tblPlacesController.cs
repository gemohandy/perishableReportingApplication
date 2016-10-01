using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using API2.Models;

namespace API2.Controllers
{
    public class tblPlacesController : ApiController
    {
        private dbPerishableEntities db = new dbPerishableEntities();

        // GET: api/tblPlaces
        public IQueryable<tblPlace> GettblPlaces()
        {
            return db.tblPlaces;
        }

        // GET: api/tblPlaces/5
        [ResponseType(typeof(tblPlace))]
        public IHttpActionResult GettblPlace(int id)
        {
            tblPlace tblPlace = db.tblPlaces.Find(id);
            if (tblPlace == null)
            {
                return NotFound();
            }

            return Ok(tblPlace);
        }

        // PUT: api/tblPlaces/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PuttblPlace(int id, tblPlace tblPlace)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tblPlace.Id)
            {
                return BadRequest();
            }

            db.Entry(tblPlace).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!tblPlaceExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/tblPlaces
        [ResponseType(typeof(tblPlace))]
        public IHttpActionResult PosttblPlace(tblPlace tblPlace)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.tblPlaces.Add(tblPlace);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tblPlace.Id }, tblPlace);
        }

        // DELETE: api/tblPlaces/5
        [ResponseType(typeof(tblPlace))]
        public IHttpActionResult DeletetblPlace(int id)
        {
            tblPlace tblPlace = db.tblPlaces.Find(id);
            if (tblPlace == null)
            {
                return NotFound();
            }

            db.tblPlaces.Remove(tblPlace);
            try
            {
                db.SaveChanges();
            }
            catch(Exception ex)
            {

                   String message = ex.InnerException.ToString();

            }

            return Ok(tblPlace);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool tblPlaceExists(int id)
        {
            return db.tblPlaces.Count(e => e.Id == id) > 0;
        }
    }
}