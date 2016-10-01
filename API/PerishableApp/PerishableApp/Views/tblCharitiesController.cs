using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PerishableApp.Models;

namespace PerishableApp.Views
{
    public class tblCharitiesController : Controller
    {
        private Perishableapp db = new Perishableapp();

        // GET: tblCharities
        public ActionResult Index()
        {
            var tblCharities = db.tblCharities.Include(t => t.tblPlace);
            return View(tblCharities.ToList());
        }

        // GET: tblCharities/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCharity tblCharity = db.tblCharities.Find(id);
            if (tblCharity == null)
            {
                return HttpNotFound();
            }
            return View(tblCharity);
        }

        // GET: tblCharities/Create
        public ActionResult Create()
        {
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name");
            return View();
        }

        // POST: tblCharities/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,fk_PlaceID")] tblCharity tblCharity)
        {
            if (ModelState.IsValid)
            {
                db.tblCharities.Add(tblCharity);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCharity.fk_PlaceID);
            return View(tblCharity);
        }

        // GET: tblCharities/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCharity tblCharity = db.tblCharities.Find(id);
            if (tblCharity == null)
            {
                return HttpNotFound();
            }
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCharity.fk_PlaceID);
            return View(tblCharity);
        }

        // POST: tblCharities/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,fk_PlaceID")] tblCharity tblCharity)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tblCharity).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.fk_PlaceID = new SelectList(db.tblPlaces, "Id", "Name", tblCharity.fk_PlaceID);
            return View(tblCharity);
        }

        // GET: tblCharities/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            tblCharity tblCharity = db.tblCharities.Find(id);
            if (tblCharity == null)
            {
                return HttpNotFound();
            }
            return View(tblCharity);
        }

        // POST: tblCharities/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            tblCharity tblCharity = db.tblCharities.Find(id);
            db.tblCharities.Remove(tblCharity);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
